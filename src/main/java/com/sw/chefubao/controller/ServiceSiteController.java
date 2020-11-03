package com.sw.chefubao.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.sw.chefubao.common.R;
import com.sw.chefubao.common.config.ApplicaTionYmlConfig;
import com.sw.chefubao.common.util.FileUtils;
import com.sw.chefubao.common.util.GlobaCoordinatesUtil;
import com.sw.chefubao.entity.ServiceSite;
import com.sw.chefubao.service.ServiceSiteService;
import com.sw.chefubao.vo.ServiceSiteVo;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GlobalCoordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 服务点
 */
@RestController
@RequestMapping("/serviceSite")
public class ServiceSiteController {

    @Autowired
    private ServiceSiteService serviceSiteService;
    @Autowired
    private ApplicaTionYmlConfig applicaTionYmlConfig;
    private final String filePath = "/servicesite/";

    /**
     * 所有服务点
     *
     * @return
     */
    @GetMapping("/list")
    public R list() {
        List<ServiceSite> list = serviceSiteService.list();
        return R.SELECT_SUCCESS.data(list);
    }

    /**
     * 根据定位获取最近的5个服务点
     *
     * @param longitude
     * @param latitude
     * @return
     */
    @PostMapping("/list")
    public R listForFive(@RequestParam("longitude") String longitude, @RequestParam("latitude") String latitude, Integer number) {
        if (ObjectUtil.isEmpty(number)) {
            number = 5;
        }
        GlobalCoordinates source = new GlobalCoordinates(Double.valueOf(latitude), Double.valueOf(longitude));
        List<ServiceSite> list = serviceSiteService.list();
        List<ServiceSiteVo> serviceSiteVos = new ArrayList<>();
        list.forEach((item) -> {
            GlobalCoordinates target = new GlobalCoordinates(Double.valueOf(item.getLatitude()), Double.valueOf(item.getLongitude()));
            int distance = GlobaCoordinatesUtil.getDistanceMeter(source, target);
            ServiceSiteVo serviceSiteVo = BeanUtil.toBean(item, ServiceSiteVo.class);
            serviceSiteVo.setDistance(distance);
            serviceSiteVos.add(serviceSiteVo);
        });

        Collections.sort(serviceSiteVos);
        if (serviceSiteVos.size() >= 5) {
            List<ServiceSiteVo> serviceSiteVoList = serviceSiteVos.subList(0, number);
            return R.SELECT_SUCCESS.data(serviceSiteVoList);
        }
        return R.SELECT_SUCCESS.data(serviceSiteVos);
    }


    /**
     * 服务点删除
     *
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public R delete(@RequestParam("id") Integer id) {
        boolean b = serviceSiteService.removeById(id);
        if (b) {
            return R.DELETE_SUCCESS;
        }
        return R.DELETE_ERROR;
    }

    /**
     * 服务点保存
     */
    @PostMapping("/save")
    public R save(@RequestParam("name") String name, @RequestParam("address") String address,
                  @RequestParam("longitude") String longitude, @RequestParam("latitude") String latitude,
                  MultipartFile file) {
        ServiceSite serviceSite = new ServiceSite();
        if (file != null) {
            String imgLocaltion = applicaTionYmlConfig.getFilePath() + filePath;
            String fileName = FileUtils.upload(imgLocaltion, file);
            serviceSite.setPhoto(imgLocaltion + fileName);
        }
        serviceSite.setAddress(address);
        serviceSite.setName(name);
        serviceSite.setLatitude(latitude);
        serviceSite.setLongitude(longitude);
        boolean save = serviceSiteService.save(serviceSite);
        if (!save) {
            return R.SAVE_ERROR;
        }
        return R.SAVE_SUCCESS;
    }
}

package com.sw.chefubao.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sw.chefubao.common.R;
import com.sw.chefubao.common.config.ApplicaTionYmlConfig;
import com.sw.chefubao.common.util.FileUtils;
import com.sw.chefubao.entity.InstallmentBuyCar;
import com.sw.chefubao.service.InstallmentBuyCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * 分期买车
 */
@RestController
@RequestMapping("/installmentBuyCar")
public class InstallmentBuyCarController {
    @Autowired
    private ApplicaTionYmlConfig applicaTionYmlConfig;
    @Autowired
    private InstallmentBuyCarService installmentBuyCarService;
    /**
     * 录播图上传路径
     */
    private final String filePath = "/installment/";

    /**
     * 查询买车分期在售车辆
     *
     * @param number 显示的车辆的数量 无参为所有车辆
     * @return
     */
    @GetMapping("/list")
    public R listByType(Integer number) {
        if (ObjectUtil.isNotEmpty(number)) {
            List<InstallmentBuyCar> list = installmentBuyCarService.listForLimit(number);
            return R.SELECT_SUCCESS.data(list);
        } else {
            QueryWrapper<InstallmentBuyCar> queryWrapper = new QueryWrapper<>();
            List<InstallmentBuyCar> list = installmentBuyCarService.list(queryWrapper.orderByDesc("update_time"));
            return R.SELECT_SUCCESS.data(list);
        }
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public R delete(@RequestParam("id") Integer id) {
        boolean b = installmentBuyCarService.removeById(id);
        if (b) {
            return R.DELETE_SUCCESS;
        }
        return R.DELETE_ERROR;
    }

    /**
     * 上传分期买车
     */
    @PostMapping("/save")
    public R save(Integer id, @RequestParam("name") String name, MultipartFile file) {
        InstallmentBuyCar installmentBuyCar = new InstallmentBuyCar();
        installmentBuyCar.setId(id);
        installmentBuyCar.setName(name);
        if (file != null) {
            String imgLocaltion = applicaTionYmlConfig.getFilePath() + filePath;
            String fileName = FileUtils.upload(imgLocaltion, file);
            installmentBuyCar.setImgPath(imgLocaltion + fileName);
        }
        installmentBuyCar.setUpdateTime(new Date());
        installmentBuyCarService.saveOrUpdate(installmentBuyCar);
        return R.SAVE_SUCCESS;
    }
}

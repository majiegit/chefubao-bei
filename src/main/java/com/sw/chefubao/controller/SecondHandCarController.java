package com.sw.chefubao.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sw.chefubao.common.R;
import com.sw.chefubao.common.config.ApplicaTionYmlConfig;
import com.sw.chefubao.common.enums.SecondHandCarStatusEnum;
import com.sw.chefubao.common.util.FileUtils;
import com.sw.chefubao.entity.CarType;
import com.sw.chefubao.entity.DrivingLicense;
import com.sw.chefubao.entity.SecondHandCar;
import com.sw.chefubao.entity.UserCar;
import com.sw.chefubao.service.CarTypeService;
import com.sw.chefubao.service.DrivingLicenseService;
import com.sw.chefubao.service.SecondHandCarService;
import com.sw.chefubao.service.UserCarService;
import com.sw.chefubao.vo.SecondHandCarVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 二手车
 */
@RestController
@RequestMapping("/secondHandCar")
public class SecondHandCarController {

    @Autowired
    private SecondHandCarService secondHandCarService;
    @Autowired
    private DrivingLicenseService drivingLicenseService;
    @Autowired
    private ApplicaTionYmlConfig applicaTionYmlConfig;
    @Autowired
    private UserCarService userCarService;
    @Autowired
    private CarTypeService carTypeService;
    /**
     * 二手车出售登记上传车辆图片路径
     */
    private final String filePath = "/secondhandcar/";

    /**
     * 在售车辆列表
     *
     * @return
     */
    @GetMapping("/list")
    public R list() {
        SecondHandCar secondHandCar = new SecondHandCar();
        secondHandCar.setStatus(SecondHandCarStatusEnum.ON_SOLD.getKey());
        QueryWrapper<SecondHandCar> queryWrapper = new QueryWrapper<>(secondHandCar);
        List<SecondHandCar> list = secondHandCarService.list(queryWrapper.orderByDesc("update_time"));
        LinkedList<SecondHandCarVo> secondHandCars = new LinkedList<>();
        list.forEach((item) -> {
            SecondHandCarVo secondHandCarVo = BeanUtil.toBean(item, SecondHandCarVo.class);
            secondHandCarVo.setPrice(item.getPrice() * 0.01);
            secondHandCars.add(secondHandCarVo);
        });
        return R.SELECT_SUCCESS.data(secondHandCars);
    }

    /**
     * 用户售车管理列表
     *
     * @return
     */
    @GetMapping("/listByUser")
    public R listByUser(@RequestParam("userId") Integer userId) {
        DrivingLicense drivingLicense = new DrivingLicense();
        drivingLicense.setUserId(userId);
        QueryWrapper<DrivingLicense> queryWrapperDrivingLicense = new QueryWrapper<>(drivingLicense);
        LinkedList<Integer> drivingLicenseIdList = new LinkedList<>();
        List<DrivingLicense> list1 = drivingLicenseService.list(queryWrapperDrivingLicense);
        if (list1.size() == 0) {
            return R.SELECT_ERROR;
        }
        list1.forEach((item) -> drivingLicenseIdList.add(item.getId()));
        QueryWrapper<SecondHandCar> queryWrapper = new QueryWrapper<>();
        List<SecondHandCar> list = secondHandCarService.list(queryWrapper.in("driving_license_id", drivingLicenseIdList));
        return R.SELECT_SUCCESS.data(list);
    }

    /**
     * 更新车辆已售状态
     *
     * @param id
     * @return
     */
    @PostMapping("/updateStatus")
    public R save(@RequestParam("id") Integer id) {
        SecondHandCar secondHandCar = secondHandCarService.getById(id);
        secondHandCar.setStatus(SecondHandCarStatusEnum.OUT_SOLD.getKey());
        boolean b = secondHandCarService.updateById(secondHandCar);
        if (b) {
            return R.UPDATE_SUCCESS;
        }
        return R.UPDATE_ERROR;
    }

    /**
     * 保存   新增or 更新
     *
     * @param SecondHandCar
     * @return
     */
    @PostMapping("/save")
    public R save(@RequestBody SecondHandCar SecondHandCar) {

        boolean b = secondHandCarService.saveOrUpdate(SecondHandCar);
        if (b) {
            return R.SAVE_SUCCESS;
        }
        return R.SAVE_ERROR;

    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public R delete(@RequestParam("id") Integer id) {
        boolean b = secondHandCarService.removeById(id);
        if (b) {
            return R.DELETE_SUCCESS;
        }
        return R.DELETE_ERROR;
    }

    /**
     * 个人车辆出售登记
     *
     * @param drivingLicenseId 行驶证ID
     * @param contacts         联系人
     * @param phone            电话
     * @param price            出售价格 单位（元）
     * @return
     */
    @PostMapping("/register")
    public R register(@RequestParam("drivingLicenseId") String drivingLicenseId,
                      @RequestParam("contacts") String contacts, @RequestParam("phone") String phone,
                      @RequestParam("price") Double price, @RequestParam("description") String description) {
        Double serviceCharge = 9.9;
        // 行驶证信息
        DrivingLicense drivingLicense = drivingLicenseService.getById(drivingLicenseId);
        UserCar userCar = userCarService.getById(drivingLicense.getCarId());
        CarType carType = carTypeService.getById(userCar.getCarTypeId());
        // 封装二手车信息
        SecondHandCar secondHandCar = new SecondHandCar();
        secondHandCar.setBrand(userCar.getBrand());
        secondHandCar.setType(carType.getCarTypeName());
        secondHandCar.setDescription(description);
        secondHandCar.setColor(userCar.getColor());
        secondHandCar.setPrice(NumberUtil.round(price * 100, 0).intValue());
        secondHandCar.setBuyTime(userCar.getBuyDate());
        secondHandCar.setDrivingLicenseId(NumberUtil.parseInt(drivingLicenseId));
        secondHandCar.setContactName(contacts);
        secondHandCar.setContactPhone(phone);
        secondHandCar.setUpdateTime(new Date());
        secondHandCar.setServiceCharge(NumberUtil.round(serviceCharge * 100, 0).intValue());
        secondHandCar.setStatus(SecondHandCarStatusEnum.ON_SOLD.getKey());
        boolean save = secondHandCarService.save(secondHandCar);
        if (save) {
            return R.SAVE_SUCCESS;
        }
        return R.SAVE_ERROR;
    }

    /**
     * 上传出售车辆照片文件1
     *
     * @param drivingLicenseId
     * @param file
     * @return
     */
    @PostMapping("/upload/file1")
    public R upload1(@RequestParam("drivingLicenseId") Integer drivingLicenseId,
                     @RequestParam("file") MultipartFile file) {
        //上传车辆照片
        String imgPath = applicaTionYmlConfig.getFilePath() + filePath;
        String fileNameOne = FileUtils.upload(imgPath, file);

        boolean save = secondHandCarService.updateFile1(imgPath + fileNameOne, drivingLicenseId);
        if (!save) {
            return R.UPLOAD_ERROR;
        }
        return R.UPLOAD_SUCCESS;

    }

    /**
     * 上传出售车辆照片文件2
     *
     * @param drivingLicenseId
     * @param file
     * @return
     */
    @PostMapping("/upload/file2")
    public R upload2(@RequestParam("drivingLicenseId") Integer drivingLicenseId,
                     @RequestParam("file") MultipartFile file) {
        //上传车辆照片
        String imgPath = applicaTionYmlConfig.getFilePath() + filePath;
        String fileNameTwo = FileUtils.upload(imgPath, file);

        boolean save = secondHandCarService.updateFile2(imgPath + fileNameTwo, drivingLicenseId);
        if (!save) {
            return R.UPLOAD_ERROR;
        }
        return R.UPLOAD_SUCCESS;
    }

}

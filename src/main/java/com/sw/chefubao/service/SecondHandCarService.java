package com.sw.chefubao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sw.chefubao.entity.SecondHandCar;

public interface SecondHandCarService extends IService<SecondHandCar> {
    /**
     * 保存出售车辆照片1
     * @param filePath
     * @param drivingLicenseId
     * @return
     */
    boolean updateFile1(String filePath, Integer drivingLicenseId);
    /**
     * 保存出售车辆照片2
     * @param filePath
     * @param drivingLicenseId
     * @return
     */
    boolean updateFile2(String filePath, Integer drivingLicenseId);
}

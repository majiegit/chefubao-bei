package com.sw.chefubao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sw.chefubao.entity.DrivingLicense;
import com.sw.chefubao.vo.UserCarParamVo;

public interface DrivingLicenseService extends IService<DrivingLicense> {

    /**
     * 车辆注册生成行驶证
     */

    int createDrivingLicense(UserCarParamVo userCarParamVo,Integer carId);
}

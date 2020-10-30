package com.sw.chefubao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sw.chefubao.entity.UserCar;

public interface UserCarService extends IService<UserCar> {
    /**
     * 更新车辆照片路径
     *
     * @param userCarId
     * @param filePath
     * @param photoField
     * @return
     */
    int uploadFile(Integer userCarId, String filePath, String photoField);
}

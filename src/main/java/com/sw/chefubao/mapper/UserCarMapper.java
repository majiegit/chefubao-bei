package com.sw.chefubao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sw.chefubao.entity.User;
import com.sw.chefubao.entity.UserCar;
import org.apache.ibatis.annotations.Param;

/**
 * @author mj
 * 用户车辆
 */
public interface UserCarMapper extends BaseMapper<UserCar> {


    int uploadFile(@Param("userCarId") Integer userCarId, @Param("filePath") String filePath, @Param("photoField") String photoField);
}

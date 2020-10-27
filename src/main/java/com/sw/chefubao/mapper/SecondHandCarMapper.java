package com.sw.chefubao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sw.chefubao.entity.SecondHandCar;
import org.apache.ibatis.annotations.Param;

/**
 * @author mj
 * 二手车
 */
public interface SecondHandCarMapper extends BaseMapper<SecondHandCar> {

    int updateFile1(@Param("filePath") String filePath, @Param("drivingLicenseId") Integer drivingLicenseId);

    int updateFile2(@Param("filePath") String filePath, @Param("drivingLicenseId") Integer drivingLicenseId);
}

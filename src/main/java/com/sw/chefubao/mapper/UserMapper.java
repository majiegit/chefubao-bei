package com.sw.chefubao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sw.chefubao.entity.DrivingLicense;
import com.sw.chefubao.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author mj
 * 小程序用户
 */
public interface UserMapper extends BaseMapper<User> {

    int updateCardFilePathPositive(@Param("fileNameCard") String fileNameCard, @Param("userId") Integer userId);

    int updateCardFilePathNegative(@Param("fileNameCard") String fileNameCard, @Param("userId") Integer userId);

}

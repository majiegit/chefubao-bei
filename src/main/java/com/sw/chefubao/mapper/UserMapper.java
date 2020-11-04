package com.sw.chefubao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sw.chefubao.entity.DrivingLicense;
import com.sw.chefubao.entity.User;
import com.sw.chefubao.vo.UserAccountVo;
import com.sw.chefubao.vo.UserPeopleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mj
 * 小程序用户
 */
public interface UserMapper extends BaseMapper<User> {

    int updateCardFilePathPositive(@Param("fileNameCard") String fileNameCard, @Param("userId") Integer userId);

    int updateCardFilePathNegative(@Param("fileNameCard") String fileNameCard, @Param("userId") Integer userId);

    User selectUser(@Param("openId") String openId);

    Page<UserPeopleVo> peopleList(Page<UserPeopleVo> page, @Param("userType") Integer userType);

    Page<UserAccountVo> accountList(Page<UserAccountVo> page, @Param("userType") Integer userType);
}

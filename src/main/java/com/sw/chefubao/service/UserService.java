package com.sw.chefubao.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sw.chefubao.entity.User;
import com.sw.chefubao.vo.UserAccountVo;
import com.sw.chefubao.vo.UserPeopleVo;

import java.util.List;

public interface UserService extends IService<User> {

    boolean updateCardFilePathPositive(String fileNameCardOne, Integer userId);

    boolean updateCardFilePathNegative(String fileNameCardOne, Integer userId);

    User selectUser(String openId);

    Page<UserPeopleVo> peopleList(Page<UserPeopleVo> page, Integer userType);

    Page<UserAccountVo> accountList(Page<UserAccountVo> page, Integer userType);

}

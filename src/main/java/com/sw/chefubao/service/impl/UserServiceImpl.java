package com.sw.chefubao.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sw.chefubao.entity.User;
import com.sw.chefubao.mapper.UserMapper;
import com.sw.chefubao.service.UserService;
import com.sw.chefubao.vo.UserAccountVo;
import com.sw.chefubao.vo.UserPeopleVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public boolean updateCardFilePathPositive(String fileNameCard, Integer userId) {
        baseMapper.updateCardFilePathPositive(fileNameCard, userId);
        return true;
    }

    @Override
    public boolean updateCardFilePathNegative(String fileNameCard, Integer userId) {
        baseMapper.updateCardFilePathNegative(fileNameCard, userId);
        return true;
    }

    @Override
    public User selectUser(String openId) {
        return baseMapper.selectUser(openId);
    }

    @Override
    public Page<UserPeopleVo> peopleList(Page<UserPeopleVo> page, Integer userType) {
        return baseMapper.peopleList(page, userType);
    }

    @Override
    public Page<UserAccountVo> accountList(Page<UserAccountVo> page, Integer userType) {
        return baseMapper.accountList(page, userType);
    }
}

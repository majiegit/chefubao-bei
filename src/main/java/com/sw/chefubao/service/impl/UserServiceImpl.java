package com.sw.chefubao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sw.chefubao.entity.User;
import com.sw.chefubao.mapper.UserMapper;
import com.sw.chefubao.service.UserService;
import org.springframework.stereotype.Service;

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
}

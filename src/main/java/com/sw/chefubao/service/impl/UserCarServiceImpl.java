package com.sw.chefubao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sw.chefubao.entity.UserCar;
import com.sw.chefubao.mapper.UserCarMapper;
import com.sw.chefubao.service.UserCarService;
import org.springframework.stereotype.Service;

@Service
public class UserCarServiceImpl extends ServiceImpl<UserCarMapper, UserCar> implements UserCarService {
    @Override
    public int uploadFile(Integer userCarId, String filePath, String photoField) {
        int a= baseMapper.uploadFile(userCarId,filePath,photoField);
        return a;
    }
}

package com.sw.chefubao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sw.chefubao.entity.SecondHandCar;
import com.sw.chefubao.mapper.SecondHandCarMapper;
import com.sw.chefubao.service.SecondHandCarService;
import org.springframework.stereotype.Service;

@Service
public class SecondHandCarServiceImpl extends ServiceImpl<SecondHandCarMapper, SecondHandCar> implements SecondHandCarService {

    @Override
    public boolean updateFile1(String filePath, Integer drivingLicenseId) {
        baseMapper.updateFile1(filePath, drivingLicenseId);
        return true;
    }

    @Override
    public boolean updateFile2(String filePath, Integer drivingLicenseId) {
        baseMapper.updateFile2(filePath, drivingLicenseId);
        return true;
    }
}

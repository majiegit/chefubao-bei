package com.sw.chefubao.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sw.chefubao.entity.UserLicenseTagApplyInfo;
import com.sw.chefubao.mapper.UserLicenseTagApplyInfoMapper;
import com.sw.chefubao.service.UserLicenseTagApplyInfoService;
import org.springframework.stereotype.Service;

@Service
public class UserLicenseTagApplyInfoServiceImpl extends ServiceImpl<UserLicenseTagApplyInfoMapper, UserLicenseTagApplyInfo> implements UserLicenseTagApplyInfoService {
    @Override
    public IPage<UserLicenseTagApplyInfo> pageList(Page<UserLicenseTagApplyInfo> page, Integer status, Integer model, String sql) {

        return baseMapper.pageList(page, status, model, sql);
    }

    @Override
    public int statics(DateTime startTime, DateTime endTime, String address) {
        return baseMapper.statics(startTime, endTime, address);
    }

    @Override
    public int staticsTotal(String address,Integer typeId) {
        return baseMapper.staticsTotal(address,typeId);
    }

    @Override
    public int staticsTotalByTime(String address, Integer typeId, DateTime startTime, DateTime endTime) {
        return baseMapper.staticsTotalByTime(address,typeId,startTime,endTime);
    }
}

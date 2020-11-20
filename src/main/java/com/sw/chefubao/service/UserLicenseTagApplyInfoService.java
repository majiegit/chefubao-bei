package com.sw.chefubao.service;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sw.chefubao.entity.UserLicenseTagApplyInfo;

public interface UserLicenseTagApplyInfoService extends IService<UserLicenseTagApplyInfo> {

    IPage<UserLicenseTagApplyInfo> pageList(Page<UserLicenseTagApplyInfo> page, Integer status, Integer mode, String sql);

    int statics(DateTime startTime, DateTime endTime, String address);

    int staticsTotal(String address,Integer typeId);

    int staticsTotalByTime(String address, Integer id, DateTime startTime, DateTime endTime);
}

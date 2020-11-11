package com.sw.chefubao.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sw.chefubao.entity.UserLicenseTagApplyInfo;

public interface UserLicenseTagApplyInfoService extends IService<UserLicenseTagApplyInfo> {

    IPage<UserLicenseTagApplyInfo> pageList(Page<UserLicenseTagApplyInfo> page, Integer status, Integer model, String sql);
}

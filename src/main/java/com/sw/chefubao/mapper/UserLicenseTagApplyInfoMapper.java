package com.sw.chefubao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sw.chefubao.entity.UserLicenseTagApplyInfo;
import org.apache.ibatis.annotations.Param;

/**
 * @author mj
 * 申领信息
 */
public interface UserLicenseTagApplyInfoMapper extends BaseMapper<UserLicenseTagApplyInfo> {

    IPage<UserLicenseTagApplyInfo> pageList(Page<UserLicenseTagApplyInfo> page, @Param("status") Integer status, @Param("model") Integer model, @Param("sql") String sql);
}

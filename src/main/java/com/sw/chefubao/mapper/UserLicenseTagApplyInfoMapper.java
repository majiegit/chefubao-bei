package com.sw.chefubao.mapper;

import cn.hutool.core.date.DateTime;
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

    IPage<UserLicenseTagApplyInfo> pageList(Page<UserLicenseTagApplyInfo> page, @Param("status") Integer status, @Param("mode") Integer mode, @Param("sql") String sql);

    int statics(@Param("startTime") DateTime startTime, @Param("endTime") DateTime endTime, @Param("address") String address);

    int staticsTotal(@Param("address") String address,@Param("typeId") Integer typeId);

    int staticsTotalByTime(@Param("address") String address, @Param("typeId") Integer typeId, @Param("startTime") DateTime startTime, @Param("endTime") DateTime endTime);
}

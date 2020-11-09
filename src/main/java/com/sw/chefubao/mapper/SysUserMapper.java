package com.sw.chefubao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sw.chefubao.entity.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * @author mj
 * 后台用户
 */
public interface SysUserMapper extends BaseMapper<SysUser> {


    SysUser getByUserName(@Param("username") String username);
}

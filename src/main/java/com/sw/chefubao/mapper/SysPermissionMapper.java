package com.sw.chefubao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sw.chefubao.entity.SysPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mj
 * 权限
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {


    List<SysPermission> selectListByPermissionId(@Param("permissionId") Integer permissionId);
}

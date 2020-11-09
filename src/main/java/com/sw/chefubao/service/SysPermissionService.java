package com.sw.chefubao.service;

import com.aliyuncs.AcsRequest;
import com.aliyuncs.AcsResponse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sw.chefubao.entity.SysPermission;

import java.util.List;

public interface SysPermissionService extends IService<SysPermission> {

    List<Object>  tree(Integer permissionId);

    /**
     * 查询本级权限
     * @param permissionId
     * @return
     */
    List<Object> selectListByPermissionId(Integer permissionId);

}

package com.sw.chefubao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sw.chefubao.entity.SysRegion;

import java.util.List;

public interface SysRegionService extends IService<SysRegion> {


    List<Object> tree(Integer permissionValue);

    List<Object> selectListByPermissionId(Integer id);

    List<SysRegion> getList(Integer pid);

    String  getRegionName(StringBuffer stringBuffer,Integer regionId);
}

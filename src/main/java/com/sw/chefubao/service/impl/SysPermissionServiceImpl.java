package com.sw.chefubao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sw.chefubao.entity.SysPermission;
import com.sw.chefubao.mapper.SysPermissionMapper;
import com.sw.chefubao.service.SysPermissionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    @Override
    public List<Object> tree(Integer permissionId) {
        List<Object> list = new ArrayList<>();
        List<SysPermission> listSysPermission = baseMapper.selectListByPermissionId(permissionId);
        for (SysPermission sysPermission : listSysPermission) {
            LinkedHashMap<String, Object> map = new LinkedHashMap<>();
            map.put("id", sysPermission.getPermissionId());
            map.put("label", sysPermission.getName());
            map.put("value",sysPermission.getPermissionValue());
            List<Object> list1 = selectListByPermissionId(sysPermission.getPermissionId());
            if (list1.size() != 0) {
                map.put("children", list1);
            }
            list.add(map);
        }

        return list;
    }

    public List<Object> selectListByPermissionId(Integer permissionId) {

        List<Object> list = new ArrayList<>();
        List<SysPermission> listResult = baseMapper.selectListByPermissionId(permissionId);
        for (SysPermission sysPermission : listResult) {
            LinkedHashMap<String, Object> map = new LinkedHashMap<>();
            map.put("id", sysPermission.getPermissionId());
            map.put("label", sysPermission.getName());
            map.put("value",sysPermission.getPermissionValue());
            List<Object> list1 = selectListByPermissionId(sysPermission.getPermissionId());
            if (list1.size() != 0) {
                map.put("children", list1);
            }
            list.add(map);
        }
        return list;
    }


}

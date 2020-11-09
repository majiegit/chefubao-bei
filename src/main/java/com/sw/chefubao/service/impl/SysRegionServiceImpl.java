package com.sw.chefubao.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sw.chefubao.entity.SysRegion;
import com.sw.chefubao.entity.SysRegion;
import com.sw.chefubao.entity.SysRegion;
import com.sw.chefubao.mapper.SysRegionMapper;
import com.sw.chefubao.service.SysRegionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;


@Service
public class SysRegionServiceImpl extends ServiceImpl<
        SysRegionMapper, SysRegion> implements SysRegionService {


    @Override
    public List<Object> tree(Integer permissionValue) {

        List<Object> list = new ArrayList<>();
        List<Object> list1 = new ArrayList<>();
        if (ObjectUtil.isEmpty(permissionValue)) {
            permissionValue = 0;
            List<SysRegion> listSysRegion = baseMapper.selectListByPermissionId(permissionValue);
            for (SysRegion sysRegion : listSysRegion) {
                LinkedHashMap<String, Object> map = new LinkedHashMap<>();
                map.put("id", sysRegion.getId());
                map.put("label", sysRegion.getName());
                map.put("children", list1);
                list.add(map);
            }
            return list;
        } else {
            List<SysRegion> listSysRegion = baseMapper.selectListByPermissionId(permissionValue);
            for (SysRegion sysRegion : listSysRegion) {
                LinkedHashMap<String, Object> map = new LinkedHashMap<>();
                map.put("id", sysRegion.getId());
                map.put("label", sysRegion.getName());
                map.put("children", selectListByPermissionId(sysRegion.getId()));
                list.add(map);
            }
            return list;
        }
    }

    @Override
    public List<Object> selectListByPermissionId(Integer id) {
        List<Object> list = new ArrayList<>();
        List<SysRegion> listResult = baseMapper.selectListByPermissionId(id);
        for (SysRegion sysRegion : listResult) {
            LinkedHashMap<String, Object> map = new LinkedHashMap<>();
            map.put("id", sysRegion.getId());
            map.put("label", sysRegion.getName());
            List<Object> list1 = selectListByPermissionId(sysRegion.getId());
            if (list1.size() != 0) {
                map.put("children", list1);
            }
            list.add(map);
        }
        return list;
    }

    @Override
    public List<SysRegion> getList(Integer pid) {

        return baseMapper.getList(pid);
    }

    @Override
    public String getRegionName(StringBuffer stringBuffer, Integer regionId) {
        SysRegion sysRegion = baseMapper.selectById(regionId);
        if (ObjectUtil.isNotEmpty(sysRegion)) {
            stringBuffer.insert(0,sysRegion.getName()+"-");
            getRegionName(stringBuffer, sysRegion.getPid());
        }
        return stringBuffer.toString();
    }
}
package com.sw.chefubao.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sw.chefubao.common.R;
import com.sw.chefubao.entity.SysPermission;
import com.sw.chefubao.entity.SysRegion;
import com.sw.chefubao.service.SysPermissionService;
import com.sw.chefubao.service.SysRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@RestController
@RequestMapping("/sysPermission")
public class SysPermissionController {
    @Autowired
    private SysPermissionService sysPermissionService;

    @Autowired
    private SysRegionService sysRegionService;

    /**
     * 添加权限
     *
     * @param name
     * @param permissionValue
     * @return
     */
    @PostMapping("/addByParentId")
    public R saveCategoryById(@RequestParam("name") String name, @RequestParam("regionId") Integer regionId,
                              @RequestParam("permissionValue") Integer permissionValue) {
        SysPermission sysPermission = new SysPermission();
        sysPermission.setName(name);
        sysPermission.setRegionId(regionId);
        sysPermission.setCreateTime(new Date());
        sysPermission.setType(1);
        sysPermission.setPermissionValue(permissionValue.toString());
        boolean save = sysPermissionService.save(sysPermission);
        if (save) {
            return R.SAVE_SUCCESS;
        }
        return R.SAVE_ERROR;
    }

    /**
     * 权限分页查询
     *
     * @param page
     * @return
     */

    @PostMapping("/page")
    public R saveCategoryById(Page<SysPermission> page, Integer regionId) {
        LinkedList<Integer> integers = new LinkedList<>();
        integers.add(regionId);
        List<SysRegion> list = sysRegionService.getList(regionId);
        list.forEach((item -> {
            integers.add(item.getId());
        }));

        QueryWrapper<SysPermission> sysPermissionQueryWrapper = new QueryWrapper<>();
        IPage<SysPermission> page1 = sysPermissionService.page(page, sysPermissionQueryWrapper.in("region_id", integers));
        return R.SELECT_SUCCESS.data(page1);
    }

    /**
     * 权限列表
     *
     * @return
     */
    @PostMapping("/list")
    public R list(Integer regionId) {

        LinkedList<Integer> integers = new LinkedList<>();
        integers.add(regionId);
        List<SysRegion> list = sysRegionService.getList(regionId);
        list.forEach((item -> {
            integers.add(item.getId());
        }));

        QueryWrapper<SysPermission> sysPermissionQueryWrapper = new QueryWrapper<>();
        return R.SELECT_SUCCESS.data(sysPermissionService.list(sysPermissionQueryWrapper.in("region_id", integers)));
    }
}

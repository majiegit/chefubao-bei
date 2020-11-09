package com.sw.chefubao.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sw.chefubao.common.R;
import com.sw.chefubao.common.enums.SysUserTypeEnum;
import com.sw.chefubao.common.enums.UserTypeEnum;
import com.sw.chefubao.entity.*;
import com.sw.chefubao.service.*;
import com.sw.chefubao.vo.UserAccountVo;
import com.sw.chefubao.vo.UserPeopleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/sysUser")
public class SysUserController {
    @Autowired
    private UserService userService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysPermissionService sysPermissionService;
    @Autowired
    private SysRegionService sysRegionService;
    @Autowired
    private UserLicenseTagApplyInfoService userLicenseTagApplyInfoService;

    /**
     * 后台账号登陆
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public R sysLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
        if (ObjectUtil.isEmpty(username)) {
            return new R(506, "请输入账号");
        }
        if (ObjectUtil.isEmpty(password)) {
            return new R(506, "请输入密码");
        }
        SysUser sysUser = sysUserService.getByUserName(username);
        if (ObjectUtil.isEmpty(sysUser)) {
            return new R(506, "账号不存在");
        }
        if (!password.equals(sysUser.getPassword())) {
            return new R(506, "密码输入错误,请重新输入");
        } else {

            SysPermission sysPermission = sysPermissionService.getById(sysUser.getPermissionId());

            HashMap<String, Object> map = new HashMap<>();
            ArrayList<String> strings = new ArrayList<>();
            strings.add(sysPermission.getType().toString());
            map.put("roles", strings);
            map.put("name", sysUser.getUsername());
            map.put("token", sysUser.getUsername());
            map.put("introduction", sysUser.getUsername());
            map.put("avatar", sysUser.getUsername());
            map.put("userInfo", sysPermission);
            return R.LOGIN_SUCCESS.data(map);
        }
    }

    /**
     * 后台企业账号发放
     */
    @PostMapping("/sendAccount")
    public R sendAccount(@RequestParam("username") String username, @RequestParam("password") String password
    ) {

        User user = new User();
        user.setUsername(username);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>(user);
        List<User> list = userService.list(userQueryWrapper);
        if (!ObjectUtil.isEmpty(list)) {
            return new R(506, "该账号已存在,请重新输入!");
        }
        user.setPassword(password);
        user.setUserType(UserTypeEnum.USER_ACCOUNT.getKey());
        userService.save(user);
        return new R(200, "添加成功");
    }

    /**
     * 后台企业账号数量增加
     */
    @PostMapping("/insertNumber")
    public R insertNumber(@RequestParam("id") Integer id, @RequestParam("number") Integer number) {
        User user = new User();
        user.setId(id);
        User byId = userService.getById(id);
        user.setTotalNumber(byId.getTotalNumber() + number);
        userService.updateById(user);
        return new R(200, "更新成功");
    }

    /**
     * 用户分页
     *
     * @param userType
     * @return
     */
    @PostMapping("/userList")
    public R getUserList(Integer current, Integer userType) {

        if (UserTypeEnum.USER_PEOPLE.getKey().equals(userType)) {
            // 个人
            Page<UserPeopleVo> page = new Page<>();
            page.setCurrent(current);
            Page<UserPeopleVo> userPeopleVos = userService.peopleList(page, userType);
            return R.SELECT_SUCCESS.data(userPeopleVos);
        } else if (UserTypeEnum.USER_ACCOUNT.getKey().equals(userType)) {
            //企业
            Page<UserAccountVo> page = new Page<>();
            page.setCurrent(current);
            Page<UserAccountVo> userAccountVos = userService.accountList(page, userType);
            return R.SELECT_SUCCESS.data(userAccountVos);
        }
        return R.SELECT_ERROR;
    }

    /**
     * 账号注册
     */
    @PostMapping("/addUsername")
    public R addUsername(@RequestParam("username") String username, @RequestParam("password") String password,
                         @RequestParam("permissionId") Integer permissionId, @RequestParam("type") String type, Integer duration) {
        if (ObjectUtil.isEmpty(username)) {
            return new R(506, "请输入账号");
        }
        if (ObjectUtil.isEmpty(password)) {
            return new R(506, "请输入密码");
        }
        SysUser user = new SysUser();
        user.setUsername(username);
        QueryWrapper<SysUser> userQueryWrapper = new QueryWrapper<>(user);
        List<SysUser> list = sysUserService.list(userQueryWrapper);
        if (!ObjectUtil.isEmpty(list)) {
            return new R(506, "该账号已存在,请重新输入!");
        }
        user.setPassword(password);
        Date date = new Date();
        user.setAddTime(date);
        user.setEndTime(DateUtil.offsetDay(date, duration));
        user.setPermissionId(permissionId);
        user.setDuration(duration);
        user.setType(NumberUtil.parseInt(type));
        sysUserService.save(user);
        return new R(200, "添加成功");
    }

    /**
     * 账号分页查询
     *
     * @param page
     * @return
     */

    @PostMapping("/page")
    public R saveCategoryById(Page<SysUser> page, Integer regionId) {
        LinkedList<Integer> ids = new LinkedList<>();
        // if (!regionId.equals(0)) {
        LinkedList<Integer> integers = new LinkedList<>();
        integers.add(regionId);
        List<SysRegion> list = sysRegionService.getList(regionId);
        list.forEach((item -> {
            integers.add(item.getId());
        }));
        QueryWrapper<SysPermission> queryWrapper = new QueryWrapper<>();
        List<SysPermission> sysPermissions = sysPermissionService.list(queryWrapper.in("region_id", integers));
        sysPermissions.forEach((item) -> {
            ids.add(item.getPermissionId());
        });
      /*  } else {
            List<SysPermission> sysPermissions = sysPermissionService.list();
            sysPermissions.forEach((item) -> {
                ids.add(item.getPermissionId());
            });
        }
        */

        QueryWrapper<SysUser> sysUserQueryWrapper = new QueryWrapper<>();
        IPage<SysUser> page1 = sysUserService.page(page, sysUserQueryWrapper.in("permission_id", ids));
        return R.SELECT_SUCCESS.data(page1);
    }

    /**
     * 后台账号小程序用户数量
     */
    @GetMapping("/getNumber")
    public R getListBySysUser(String name) {
        LinkedList<Map> maps = new LinkedList<>();
        SysUser sysUser = sysUserService.getByUserName(name);
        Integer regionId = sysPermissionService.getById(sysUser.getPermissionId()).getRegionId();
        StringBuffer stringBuffer1 = new StringBuffer();
        String regionName = sysRegionService.getRegionName(stringBuffer1, regionId);
        String substring = "";
        if (regionName.length() != 0) {
            substring = regionName.substring(0, regionName.length() - 1);
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        int address = userService.count(userQueryWrapper.like("address", substring));
        HashMap<String, Object> map = new HashMap<>();
        map.put("count", address);
        map.put("title", "当前账号地区小程序用户总数");
        maps.add(map);
        QueryWrapper<UserLicenseTagApplyInfo> queryWrapper = new QueryWrapper<>();
        int address1 = userLicenseTagApplyInfoService.count(queryWrapper.like("address", substring));
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("count", address1);
        map1.put("title", "当前账号地区车辆申领信息总数");
        maps.add(map1);
        return R.SELECT_SUCCESS.data(maps);
    }

    /**
     * 邮寄账号
     */
    @GetMapping("/getEmail")
    public R getEmail() {
        SysUser sysUser = new SysUser();
        sysUser.setType(SysUserTypeEnum.USER_MAIL.getKey());
        QueryWrapper<SysUser> sysUserQueryWrapper = new QueryWrapper<>(sysUser);
        List<SysUser> list = sysUserService.list(sysUserQueryWrapper);
        return R.SELECT_SUCCESS.data(list);
    }

    /**
     * 后台账号小程序用户列表
     */
    @GetMapping("/getUserList")
    public R getUserList(Page<User> page,String name) {
        SysUser sysUser = sysUserService.getByUserName(name);
        Integer regionId = sysPermissionService.getById(sysUser.getPermissionId()).getRegionId();
        StringBuffer stringBuffer1 = new StringBuffer();
        String regionName = sysRegionService.getRegionName(stringBuffer1, regionId);
        String substring = "";
        if (regionName.length() != 0) {
            substring = regionName.substring(0, regionName.length() - 1);
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        IPage<User> address = userService.page(page, userQueryWrapper.like("address", substring));
        return R.SELECT_SUCCESS.data(address);
    }


}

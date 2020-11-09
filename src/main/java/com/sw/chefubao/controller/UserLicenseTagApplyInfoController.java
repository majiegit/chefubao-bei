package com.sw.chefubao.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sw.chefubao.common.R;
import com.sw.chefubao.common.config.ApplicaTionYmlConfig;
import com.sw.chefubao.common.enums.DrivingLicenceEnum;
import com.sw.chefubao.common.enums.UserLicenseTagApplyInfoModeEnum;
import com.sw.chefubao.common.enums.UserLicenseTagApplyInfoStatusEnum;
import com.sw.chefubao.common.util.FileUtils;
import com.sw.chefubao.entity.DrivingLicense;
import com.sw.chefubao.entity.SysUser;
import com.sw.chefubao.entity.UserCar;
import com.sw.chefubao.entity.UserLicenseTagApplyInfo;
import com.sw.chefubao.service.*;
import com.sw.chefubao.vo.UserLicenseTagApplyInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/**
 * 申领信息
 */
@RestController
@RequestMapping("/applyInfo")
public class UserLicenseTagApplyInfoController {
    @Autowired
    private UserLicenseTagApplyInfoService userLicenseTagApplyInfoService;
    @Autowired
    private DrivingLicenseService drivingLicenseService;
    @Autowired
    private UserCarService userCarService;
    @Autowired
    private ApplicaTionYmlConfig applicaTionYmlConfig;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysPermissionService sysPermissionService;
    @Autowired
    private SysRegionService sysRegionService;
    /**
     * 录播图上传路径
     */
    private final String filePath = "/phonecar/";

    /**
     * 小程序用户申领信息查询
     *
     * @param userId
     * @return
     */
    @PostMapping("/getByUserId")
    public R getByUserId(@RequestParam("userId") Integer userId) {
        UserLicenseTagApplyInfo userLicenseTagApplyInfo = new UserLicenseTagApplyInfo();
        userLicenseTagApplyInfo.setUserId(userId);
        QueryWrapper<UserLicenseTagApplyInfo> queryWrapper = new QueryWrapper<>(userLicenseTagApplyInfo);
        List<UserLicenseTagApplyInfo> list = userLicenseTagApplyInfoService.list(queryWrapper);
        LinkedList<UserLicenseTagApplyInfoVo> userLicenseTagApplyInfoVos = new LinkedList<>();
        list.forEach((item) -> {
            Integer userCarId = item.getCarId();
            UserCar userCar = userCarService.getById(userCarId);
            UserLicenseTagApplyInfoVo userLicenseTagApplyInfoVo = BeanUtil.toBean(item, UserLicenseTagApplyInfoVo.class);
            userLicenseTagApplyInfoVo.setUserCar(userCar);
            userLicenseTagApplyInfoVos.add(userLicenseTagApplyInfoVo);
        });
        return R.SELECT_SUCCESS.data(userLicenseTagApplyInfoVos);
    }

    /**
     * 邮寄方式保存
     *
     * @return
     */
    @PostMapping("/saveMail")
    public R saveMail(@RequestParam("userId") Integer userId, @RequestParam("userCarId") Integer userCarId,
                      @RequestParam("username") String username, @RequestParam("phone") String phone,
                      @RequestParam("address") String address, @RequestParam("detailedAddress") String detailedAddress) {
        UserLicenseTagApplyInfo userLicenseTagApplyInfo = new UserLicenseTagApplyInfo();
        userLicenseTagApplyInfo.setCarId(userCarId);
        userLicenseTagApplyInfo.setUserId(userId);
        userLicenseTagApplyInfo.setUsername(username);
        userLicenseTagApplyInfo.setPhone(phone);
        userLicenseTagApplyInfo.setAddress(address);
        userLicenseTagApplyInfo.setDetailedAddress(detailedAddress);
        userLicenseTagApplyInfo.setMode(UserLicenseTagApplyInfoModeEnum.MODE_MAIL.getKey());
        userLicenseTagApplyInfo.setStatus(UserLicenseTagApplyInfoStatusEnum.APPLY_STATUS_NOT.getKey());
        userLicenseTagApplyInfo.setCreateTime(new Date());
        boolean save = userLicenseTagApplyInfoService.save(userLicenseTagApplyInfo);
        if (!save) {
            return R.SAVE_ERROR;
        }
        return R.SAVE_SUCCESS;
    }

    /**
     * 服务点保存
     *
     * @return
     */
    @PostMapping("/saveService")
    public R saveService(@RequestParam("userId") Integer userId, @RequestParam("userCarId") Integer userCarId) {
        UserLicenseTagApplyInfo userLicenseTagApplyInfo = new UserLicenseTagApplyInfo();
        userLicenseTagApplyInfo.setCarId(userCarId);
        userLicenseTagApplyInfo.setUserId(userId);
        userLicenseTagApplyInfo.setCreateTime(new Date());
        userLicenseTagApplyInfo.setMode(UserLicenseTagApplyInfoModeEnum.MODE_SERVICE.getKey());
        userLicenseTagApplyInfo.setStatus(UserLicenseTagApplyInfoStatusEnum.APPLY_STATUS_NOT.getKey());
        boolean save = userLicenseTagApplyInfoService.save(userLicenseTagApplyInfo);
        if (!save) {
            return R.SAVE_ERROR;
        }
        return R.SAVE_SUCCESS;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public R delete(@RequestParam("id") Integer id) {
        boolean b = userLicenseTagApplyInfoService.removeById(id);
        if (b) {
            return R.DELETE_SUCCESS;
        }
        return R.DELETE_ERROR;
    }

    /**
     * 绑定车牌
     *
     * @param carId
     * @param carNum
     * @param file
     * @return
     */
    @PostMapping("/bindLicense")
    @Transactional
    public R bindLicense(@RequestParam("carId") Integer carId, @RequestParam("carNum") String carNum, MultipartFile file) {
        QueryWrapper<UserLicenseTagApplyInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("car_id", carId);
        UserLicenseTagApplyInfo userLicenseTagApplyInfoResult = userLicenseTagApplyInfoService.getOne(queryWrapper);
        if (userLicenseTagApplyInfoResult.getStatus().equals(UserLicenseTagApplyInfoStatusEnum.APPLY_STATUS_NOT.getKey())) {
            return new R(506, "后台未为您提供牌照信息，暂时不可以绑定车牌");
        }
        if (carNum.equals(userLicenseTagApplyInfoResult.getCarNum())) {
            UserLicenseTagApplyInfo userLicenseTagApplyInfo = new UserLicenseTagApplyInfo();
            if (file != null) {
                String imgLocaltion = applicaTionYmlConfig.getFilePath() + filePath;
                String fileName = FileUtils.upload(imgLocaltion, file);
                userLicenseTagApplyInfo.setPeopleCarPhoto(imgLocaltion + fileName);
            } else {
                return new R(506, "请上传人车合照照片");
            }
            drivingLicenseService.updateCarNumStatus(carId, carNum, DrivingLicenceEnum.CAR_NUM_OK.getKey());
            userLicenseTagApplyInfo.setStatus(UserLicenseTagApplyInfoStatusEnum.APPLY_STATUS_UP.getKey());
            userLicenseTagApplyInfoService.update(userLicenseTagApplyInfo, queryWrapper);
            return new R(200, "车牌绑定成功");
        } else {
            return new R(506, "牌照不符");
        }
    }


    /***
     *  状态 申领信息类型查询
     * @param status  0 未办理  1 已办理 2 已上牌
     * @param model
     * @return
     */
    @GetMapping("/infoList")
    public R infoList(Page<UserLicenseTagApplyInfo> page, Integer status, Integer model,String name) {

        SysUser sysUser = sysUserService.getByUserName(name);
        Integer regionId = sysPermissionService.getById(sysUser.getPermissionId()).getRegionId();
        StringBuffer stringBuffer1 = new StringBuffer();
        String regionName = sysRegionService.getRegionName(stringBuffer1, regionId);
        String substring = "";
        if (regionName.length() != 0) {
            substring = regionName.substring(0, regionName.length() - 1);
        }
        UserLicenseTagApplyInfo userLicenseTagApplyInfo = new UserLicenseTagApplyInfo();
        userLicenseTagApplyInfo.setStatus(status);
        userLicenseTagApplyInfo.setMode(model);
        QueryWrapper<UserLicenseTagApplyInfo> queryWrapper = new QueryWrapper<>(userLicenseTagApplyInfo);
        IPage<UserLicenseTagApplyInfo> page1 = userLicenseTagApplyInfoService.page(page, queryWrapper.like("address", substring));
        return R.SELECT_SUCCESS.data(page1);
    }

    /**
     * 后台派单
     */
    @PostMapping("/sendOrder")
    public R sendOrder(@RequestParam("id") Integer id, @RequestParam("sysUser") String sysUser) {
        UserLicenseTagApplyInfo userLicenseTagApplyInfo = new UserLicenseTagApplyInfo();
        userLicenseTagApplyInfo.setId(id);
        userLicenseTagApplyInfo.setSysUser(sysUser);
        userLicenseTagApplyInfo.setStatus(UserLicenseTagApplyInfoStatusEnum.APPLY_STATUS_SEND.getKey());
        userLicenseTagApplyInfoService.updateById(userLicenseTagApplyInfo);
        return R.UPDATE_SUCCESS;
    }

    /**
     * 后台账户申领信息查询
     */
    @PostMapping("/getApplyInfo")
    public R getApplyInfo(Page<UserLicenseTagApplyInfo> page, @RequestParam("sysUser") String sysUser) {
        UserLicenseTagApplyInfo userLicenseTagApplyInfo = new UserLicenseTagApplyInfo();
        userLicenseTagApplyInfo.setSysUser(sysUser);
        userLicenseTagApplyInfo.setStatus(UserLicenseTagApplyInfoStatusEnum.APPLY_STATUS_NOT.getKey());
        QueryWrapper<UserLicenseTagApplyInfo> queryWrapper = new QueryWrapper<>(userLicenseTagApplyInfo);
        IPage<UserLicenseTagApplyInfo> page1 = userLicenseTagApplyInfoService.page(page, queryWrapper);
        return R.SELECT_SUCCESS.data(page1);
    }

    /**
     * 后台上牌
     *
     * @param id
     * @param carNum
     * @return
     */
    @PostMapping("/uploadLicense")
    public R uploadLicense(@RequestParam("id") Integer id, @RequestParam("carNum") String carNum) {
        UserLicenseTagApplyInfo userLicenseTagApplyInfo = new UserLicenseTagApplyInfo();
        userLicenseTagApplyInfo.setId(id);
        userLicenseTagApplyInfo.setCarNum(carNum);
        userLicenseTagApplyInfo.setStatus(UserLicenseTagApplyInfoStatusEnum.APPLY_STATUS_SUCCESS.getKey());
        userLicenseTagApplyInfoService.updateById(userLicenseTagApplyInfo);
        return R.UPDATE_SUCCESS;
    }


}

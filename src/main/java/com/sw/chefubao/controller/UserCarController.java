package com.sw.chefubao.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sw.chefubao.common.R;
import com.sw.chefubao.common.config.ApplicaTionYmlConfig;
import com.sw.chefubao.common.enums.UserTypeEnum;
import com.sw.chefubao.common.util.FileUtils;
import com.sw.chefubao.entity.CarType;
import com.sw.chefubao.entity.User;
import com.sw.chefubao.entity.UserCar;
import com.sw.chefubao.service.CarTypeService;
import com.sw.chefubao.service.DrivingLicenseService;
import com.sw.chefubao.service.UserCarService;
import com.sw.chefubao.service.UserService;
import com.sw.chefubao.vo.UserCarParamVo;
import com.sw.chefubao.vo.UserCarVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/userCar")
public class UserCarController {

    @Autowired
    private UserCarService userCarService;
    @Autowired
    private CarTypeService carTypeService;
    @Autowired
    private UserService userService;
    @Autowired
    private ApplicaTionYmlConfig applicaTionYmlConfig;
    @Autowired
    private DrivingLicenseService drivingLicenseService;
    /**
     * 车辆上传路径
     */
    private final String filePath = "/usercar/";

    /**
     * 用户车辆信息登记
     *
     * @param userCarParamVo
     * @return
     */
    @PostMapping("/userCarSave")
    @Transactional
    public R userCarSave(@RequestBody UserCarParamVo userCarParamVo) {
        R r = new R<>();
        if (ObjectUtil.isEmpty(userCarParamVo.getCarTypeId())) {
            r.setCode(505);
            r.setMessage("请选择车辆类型");
            return r;
        }
        if (ObjectUtil.isEmpty(userCarParamVo.getCarMotorNum()) && ObjectUtil.isEmpty(userCarParamVo.getCarRackNum())) {
            r.setCode(505);
            r.setMessage("车辆电机号和机架号至少填写一项");
            return r;
        }
        if (ObjectUtil.isEmpty(userCarParamVo.getBrand())) {
            r.setCode(505);
            r.setMessage("请选择或填写车辆品牌名称");
            return r;
        }
        if (ObjectUtil.isEmpty(userCarParamVo.getColor())) {
            r.setCode(505);
            r.setMessage("请填写车辆颜色");
            return r;
        }
        if (ObjectUtil.isEmpty(userCarParamVo.getBuyDate())) {
            r.setCode(505);
            r.setMessage("请选择购买时间");
            return r;
        }
        //  校验用户类型
        User user = userService.getById(userCarParamVo.getUserId());
        Integer userType = user.getUserType();
        // 获取用户下车辆数量
        UserCar userCarParam = new UserCar();
        userCarParam.setUserId(userCarParamVo.getUserId());
        QueryWrapper<UserCar> userQueryWrapper = new QueryWrapper<>(userCarParam);
        int count = userCarService.count(userQueryWrapper);
        if (userType.equals(UserTypeEnum.USER_PEOPLE.getKey())) {
            // 个人
            if (count == 3) {
                return new R(503, "当前账号登记车辆数量为3辆，已达到最大限制");
            }
        }
        if (userType.equals(UserTypeEnum.USER_ACCOUNT.getKey())) {
            //企业
            if (count == user.getTotalNumber()) {
                return new R(503, "当前账号登记车辆数量为" + user.getTotalNumber() + "辆，已达到最大限制");
            }
            user.setRegisterNumber(count);
            userService.save(user);
        }
        UserCar userCar = BeanUtil.toBean(userCarParamVo, UserCar.class);

        boolean save = userCarService.save(userCar);
        if (save) {
            drivingLicenseService.createDrivingLicense(userCarParamVo, userCar.getId());
        } else {
            return R.SAVE_ERROR;
        }
        return R.SAVE_SUCCESS.data(userCar);
    }

    /**
     * 上传车辆照片
     *
     * @param id
     * @param photoFlied
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public R userCarSave(@RequestParam("id") Integer id, @RequestParam("photoFlied") String photoFlied, MultipartFile file) {
        if (ObjectUtil.isEmpty(file)) {
            return R.UPLOAD_ERROR;
        }
        String imgLocaltion = applicaTionYmlConfig.getFilePath() + filePath;
        String fileName = FileUtils.upload(imgLocaltion, file);
        userCarService.uploadFile(id, imgLocaltion + fileName, photoFlied);
        return R.UPLOAD_SUCCESS;
    }

    /**
     * 根据ID查询
     *
     * @param carId
     * @return
     */
    @PostMapping("/getById")
    public R get(@RequestParam("carId") Integer carId) {
        UserCar userCar = userCarService.getById(carId);
        CarType carType = carTypeService.getById(userCar.getCarTypeId());
        UserCarVo userCarVo = BeanUtil.toBean(userCar, UserCarVo.class);
        userCarVo.setCarTypeName(carType.getCarTypeName());
        return R.SELECT_SUCCESS.data(userCarVo);
    }

}

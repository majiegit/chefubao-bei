package com.sw.chefubao.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sw.chefubao.common.R;
import com.sw.chefubao.common.enums.CarTypeEnum;
import com.sw.chefubao.common.enums.SecondHandCarStatusEnum;
import com.sw.chefubao.entity.*;
import com.sw.chefubao.service.*;
import com.sw.chefubao.vo.DrivingLicenseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 * 行驶证
 */
@RestController
@RequestMapping("/drivingLicense")
public class DrivingLicenseController {

    @Autowired
    private DrivingLicenseService drivingLicenseService;
    @Autowired
    private SecondHandCarService secondHandCarService;
    @Autowired
    private CarTypeService carTypeService;
    @Autowired
    private UserCarService userCarService;
    @Autowired
    private UserService userService;
    /**
     * 查询用户下所有行驶证
     *
     * @param userId
     * @return
     */
    @GetMapping("/listByUserId")
    public R listByType(@RequestParam("userId") Integer userId) {
        User user = userService.getById(userId);
        DrivingLicense drivingLicense = new DrivingLicense();
        drivingLicense.setUserId(userId);
        QueryWrapper<DrivingLicense> queryWrapper = new QueryWrapper<>(drivingLicense);
        List<DrivingLicense> list = drivingLicenseService.list(queryWrapper);
        LinkedList<DrivingLicenseVo> drivingLicenseVos = new LinkedList<>();
        list.forEach((item) -> {
            UserCar userCar = userCarService.getById(item.getCarId());
            CarType carType = carTypeService.getById(userCar.getCarTypeId());
            DrivingLicenseVo drivingLicenseVo = new DrivingLicenseVo();
            drivingLicenseVo.setId(item.getId());
            drivingLicenseVo.setUserId(item.getUserId());
            drivingLicenseVo.setTitle(item.getTitle());
            drivingLicenseVo.setStamp(item.getStamp());
            drivingLicenseVo.setCarType(carType.getCarTypeName());
            drivingLicenseVo.setCarNum(item.getCarNum());
            drivingLicenseVo.setOwner(user.getUsername());
            drivingLicenseVo.setAddress(user.getAddress());
            drivingLicenseVo.setCarMotorNum(userCar.getCarMotorNum());
            drivingLicenseVo.setCarRackNum(userCar.getCarRackNum());
            drivingLicenseVo.setBrand(userCar.getBrand());
            drivingLicenseVo.setColor(userCar.getColor());
            drivingLicenseVo.setBuyTime(userCar.getBuyDate());
            drivingLicenseVo.setDateOfIssue(item.getDateOfIssue());
            drivingLicenseVo.setValidity(item.getValidity());
            drivingLicenseVo.setCarNumStatus(item.getCarNumStatus());
            drivingLicenseVo.setCarId(item.getCarId());
            // 校验行驶证车辆是否为禁止出售
            Integer isSell = carType.getIsSell();
            if (isSell.equals(CarTypeEnum.FORBID_SOLD.getKey())) {
                drivingLicenseVo.setStatus(CarTypeEnum.FORBID_SOLD.getKey());
            } else if (isSell.equals(CarTypeEnum.ALLOW_SOLD.getKey())) {
                drivingLicenseVo.setStatus(CarTypeEnum.ALLOW_SOLD.getKey());
                 //  校验二手车辆是否有出售记录，如果有 状态 二手车出售的状态
                SecondHandCar secondHandCar = new SecondHandCar();
                secondHandCar.setDrivingLicenseId(item.getId());
                QueryWrapper<SecondHandCar> secondHandCarQueryWrapper = new QueryWrapper<>(secondHandCar);
                SecondHandCar one = secondHandCarService.getOne(secondHandCarQueryWrapper, true);
                if (ObjectUtil.isNotEmpty(one)) {
                    drivingLicenseVo.setStatus(one.getStatus());
                } else {
                    drivingLicenseVo.setStatus(SecondHandCarStatusEnum.UN_SOLD.getKey());
                }
            }
            drivingLicenseVos.add(drivingLicenseVo);
        });
        return R.SELECT_SUCCESS.data(drivingLicenseVos);
    }

    /**
     * 根据ID 查询行驶证信息
     *
     * @param id
     * @return
     */
    @GetMapping("/getByUserId")
    public R getByUserId(@RequestParam("id") String id) {
        DrivingLicense byId = drivingLicenseService.getById(id);
        return R.SELECT_SUCCESS.data(byId);
    }


    /**
     * 删除
     *
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public R delete(@RequestParam("id") Integer id) {
        boolean b = drivingLicenseService.removeById(id);
        if (b) {
            return R.DELETE_SUCCESS;
        }
        return R.DELETE_ERROR;
    }
}

package com.sw.chefubao.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sw.chefubao.common.R;
import com.sw.chefubao.entity.DrivingLicense;
import com.sw.chefubao.service.DrivingLicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 行驶证
 */
@RestController
@RequestMapping("/drivingLicense")
public class DrivingLicenseController {

    @Autowired
    private DrivingLicenseService drivingLicenseService;

    /**
     * 查询用户下所有行驶证
     *
     * @param userId
     * @return
     */
    @GetMapping("/listByUserId")
    public R listByType(@RequestParam("userId") Integer userId) {
        DrivingLicense drivingLicense = new DrivingLicense();
        drivingLicense.setUserId(userId);
        QueryWrapper<DrivingLicense> queryWrapper = new QueryWrapper<>(drivingLicense);
        List<DrivingLicense> list = drivingLicenseService.list(queryWrapper);
        return R.SELECT_SUCCESS.data(list);
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

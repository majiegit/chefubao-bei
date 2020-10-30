package com.sw.chefubao.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sw.chefubao.common.R;
import com.sw.chefubao.entity.CarType;
import com.sw.chefubao.entity.UserCar;
import com.sw.chefubao.service.CarTypeService;
import com.sw.chefubao.service.UserCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 车辆类型
 */
@RestController
@RequestMapping("/carType")
public class CarTypeController {
    @Autowired
    private CarTypeService carTypeService;
    @Autowired
    private UserCarService userCarService;

    /**
     * 查询列表
     *
     * @return
     */
    @GetMapping("/list")
    public R list() {
        List<CarType> list = carTypeService.list();
        return R.SELECT_SUCCESS.data(list);
    }

    /**
     * 保存（save or update）
     *
     * @return
     */
    @PostMapping("/save")
    public R save(@RequestBody CarType carType) {
        boolean save = carTypeService.saveOrUpdate(carType);
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
        UserCar userCar = new UserCar();
        userCar.setCarTypeId(id);
        QueryWrapper<UserCar> userCarQueryWrapper = new QueryWrapper<>(userCar);
        List<UserCar> list = userCarService.list(userCarQueryWrapper);
        if (list.size() == 0) {
            boolean b = carTypeService.removeById(id);
            if (b) {
                return R.DELETE_SUCCESS;
            }
        } else {
            R r = new R<>();
            r.setCode(506);
            r.setMessage("用户已有该类型的车辆，禁止删除");
            return r;
        }

        return R.DELETE_ERROR;
    }
}

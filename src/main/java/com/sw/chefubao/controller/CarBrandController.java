package com.sw.chefubao.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sw.chefubao.common.R;
import com.sw.chefubao.entity.CarBrand;
import com.sw.chefubao.entity.UserCar;
import com.sw.chefubao.service.CarBrandService;
import com.sw.chefubao.service.CarBrandService;
import com.sw.chefubao.service.UserCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 车辆类型
 */
@RestController
@RequestMapping("/carBrand")
public class CarBrandController {
    @Autowired
    private CarBrandService carBrandService;

    /**
     * 查询列表
     *
     * @return
     */
    @GetMapping("/list")
    public R list(Integer status) {
        List<CarBrand> list = carBrandService.list();
        return R.SELECT_SUCCESS.data(list);
    }

    /**
     * 保存（save or update）
     *
     * @return
     */
    @PostMapping("/save")
    public R save(@RequestBody CarBrand CarBrand) {
        boolean save = carBrandService.saveOrUpdate(CarBrand);
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


        boolean b = carBrandService.removeById(id);
        if (b) {
            return R.DELETE_SUCCESS;
        }
        return R.DELETE_ERROR;
    }
}

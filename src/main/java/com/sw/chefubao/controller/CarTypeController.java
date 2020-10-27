package com.sw.chefubao.controller;


import com.sw.chefubao.common.R;
import com.sw.chefubao.entity.CarType;
import com.sw.chefubao.service.CarTypeService;
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
        boolean save = carTypeService.save(carType);
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
        boolean b = carTypeService.removeById(id);
        if (b) {
            return R.DELETE_SUCCESS;
        }
        return R.DELETE_ERROR;
    }
}

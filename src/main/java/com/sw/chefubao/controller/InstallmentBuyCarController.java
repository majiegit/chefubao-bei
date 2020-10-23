package com.sw.chefubao.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sw.chefubao.common.R;
import com.sw.chefubao.entity.InstallmentBuyCar;
import com.sw.chefubao.service.InstallmentBuyCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 分期买车
 */
@RestController
@RequestMapping("/installmentBuyCar")
public class InstallmentBuyCarController {

    @Autowired
    private InstallmentBuyCarService installmentBuyCarService;

    /**
     * 查询买车分期在售车辆
     * @param number    显示的车辆的数量 无参为所有车辆
     * @return
     */
    @GetMapping("/list")
    public R listByType(Integer number) {
        if (ObjectUtil.isNotEmpty(number)) {
            List<InstallmentBuyCar> list = installmentBuyCarService.listForLimit(number);
            return R.SELECT_SUCCESS.data(list);
        } else {
            QueryWrapper<InstallmentBuyCar> queryWrapper = new QueryWrapper<>();
            List<InstallmentBuyCar> list = installmentBuyCarService.list(queryWrapper.orderByDesc("update_time"));
            return R.SELECT_SUCCESS.data(list);
        }
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public R delete(@RequestParam("id") Integer id) {
        boolean b = installmentBuyCarService.removeById(id);
        if (b) {
            return R.DELETE_SUCCESS;
        }
        return R.DELETE_ERROR;
    }
}

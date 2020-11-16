package com.sw.chefubao.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sw.chefubao.common.R;
import com.sw.chefubao.entity.PriceConfig;
import com.sw.chefubao.service.PriceConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 价格配置
 */
@RestController
@RequestMapping("/priceConfig")
public class PriceConfigController {
    @Autowired
    private PriceConfigService priceConfigService;

    /**
     * 查询一条
     *
     * @return
     */
    @GetMapping("/get")
    public R list(String name) {
        PriceConfig priceConfig = new PriceConfig();
        priceConfig.setName(name);
        QueryWrapper<PriceConfig> priceConfigQueryWrapper = new QueryWrapper<>(priceConfig);
        PriceConfig one = priceConfigService.getOne(priceConfigQueryWrapper, true);
        return R.SELECT_SUCCESS.data(one);
    }
    /**
     * 查询列表
     *
     * @return
     */
    @GetMapping("/list")
    public R list() {
        List<PriceConfig> list = priceConfigService.list();
        return R.SELECT_SUCCESS.data(list);
    }

    /**
     * 保存（save or update）
     *
     * @return
     */
    @PostMapping("/save")
    public R save(@RequestBody PriceConfig priceConfig) {
        priceConfig.setCreateTime(new Date());
        PriceConfig priceConfig1 = new PriceConfig();
        priceConfig1.setName(priceConfig.getName());
        QueryWrapper<PriceConfig> priceConfigQueryWrapper = new QueryWrapper<>(priceConfig1);
        boolean save = priceConfigService.saveOrUpdate(priceConfig,priceConfigQueryWrapper);
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


        boolean b = priceConfigService.removeById(id);
        if (b) {
            return R.DELETE_SUCCESS;
        }
        return R.DELETE_ERROR;
    }
}

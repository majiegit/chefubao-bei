package com.sw.chefubao.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sw.chefubao.common.R;
import com.sw.chefubao.entity.ProductType;
import com.sw.chefubao.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品分类
 */
@RestController
@RequestMapping("/productType")
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    /**
     * 商品分类列表
     *
     * @return
     */
    @GetMapping("/list")
    public R list() {
        QueryWrapper<ProductType> queryWrapper = new QueryWrapper<>();
        List<ProductType> list = productTypeService.list(queryWrapper.orderByAsc("sort"));
        return R.SELECT_SUCCESS.data(list);
    }

    /**
     * 保存   新增or 更新
     *
     * @param ProductType
     * @return
     */
    @PostMapping("/save")
    public R save(@RequestBody ProductType ProductType) {

        boolean b = productTypeService.saveOrUpdate(ProductType);
        if (b) {
            return R.SAVE_SUCCESS;
        }
        return R.SAVE_ERROR;

    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public R delete(@RequestParam("id") Integer id) {
        boolean b = productTypeService.removeById(id);
        if (b) {
            return R.DELETE_SUCCESS;
        }
        return R.DELETE_ERROR;
    }
}

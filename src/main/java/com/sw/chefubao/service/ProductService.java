package com.sw.chefubao.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sw.chefubao.entity.Product;

public interface ProductService extends IService<Product> {

    Page<Product> pageList(Page<Product> page,Integer productTypeId);
}

package com.sw.chefubao.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sw.chefubao.entity.Product;
import com.sw.chefubao.mapper.ProductMapper;
import com.sw.chefubao.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    @Override
    public Page<Product> pageList(Page<Product> page,Integer productTypeId) {
        return baseMapper.pageList(page,productTypeId);
    }
}

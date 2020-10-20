package com.sw.chefubao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sw.chefubao.entity.ProductType;
import com.sw.chefubao.mapper.ProductTypeMapper;
import com.sw.chefubao.service.ProductTypeService;
import org.springframework.stereotype.Service;

@Service
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductType> implements ProductTypeService {
}

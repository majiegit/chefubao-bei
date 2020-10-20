package com.sw.chefubao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sw.chefubao.entity.ProductDetail;
import com.sw.chefubao.mapper.ProductDetailMapper;
import com.sw.chefubao.service.ProductDetailService;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailServiceImpl extends ServiceImpl<ProductDetailMapper, ProductDetail> implements ProductDetailService {
}

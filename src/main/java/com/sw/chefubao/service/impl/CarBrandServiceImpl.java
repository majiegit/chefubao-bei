package com.sw.chefubao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sw.chefubao.entity.CarBrand;
import com.sw.chefubao.mapper.CarBrandMapper;
import com.sw.chefubao.service.CarBrandService;
import org.springframework.stereotype.Service;

@Service
public class CarBrandServiceImpl extends ServiceImpl<CarBrandMapper, CarBrand> implements CarBrandService {
}

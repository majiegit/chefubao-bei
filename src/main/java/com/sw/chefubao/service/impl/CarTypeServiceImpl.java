package com.sw.chefubao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sw.chefubao.entity.CarType;
import com.sw.chefubao.mapper.CarTypeMapper;
import com.sw.chefubao.service.CarTypeService;
import org.springframework.stereotype.Service;

@Service
public class CarTypeServiceImpl extends ServiceImpl<CarTypeMapper, CarType> implements CarTypeService {
}

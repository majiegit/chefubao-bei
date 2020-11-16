package com.sw.chefubao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sw.chefubao.entity.PriceConfig;
import com.sw.chefubao.mapper.PriceConfigMapper;
import com.sw.chefubao.service.PriceConfigService;
import org.springframework.stereotype.Service;

@Service
public class PriceConfigServiceImpl extends ServiceImpl<PriceConfigMapper, PriceConfig> implements PriceConfigService {
}

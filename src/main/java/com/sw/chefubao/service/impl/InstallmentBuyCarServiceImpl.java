package com.sw.chefubao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sw.chefubao.entity.InstallmentBuyCar;
import com.sw.chefubao.mapper.InstallmentBuyCarMapper;
import com.sw.chefubao.service.InstallmentBuyCarService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstallmentBuyCarServiceImpl extends ServiceImpl<InstallmentBuyCarMapper, InstallmentBuyCar> implements InstallmentBuyCarService {
    @Override
    public List<InstallmentBuyCar> listForLimit(Integer number) {
        List<InstallmentBuyCar> list = baseMapper.listForLimit(number);
        return list;
    }
}

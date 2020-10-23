package com.sw.chefubao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sw.chefubao.entity.InstallmentBuyCar;

import java.util.List;

public interface InstallmentBuyCarService extends IService<InstallmentBuyCar> {

    List<InstallmentBuyCar> listForLimit(Integer number);
}

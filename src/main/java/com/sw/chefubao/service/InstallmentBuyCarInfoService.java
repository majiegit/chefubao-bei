package com.sw.chefubao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sw.chefubao.entity.InstallmentBuyCar;
import com.sw.chefubao.entity.InstallmentBuyCarInfo;
import com.sw.chefubao.vo.InstallmentBuyCarInfoVo;

import java.util.List;

public interface InstallmentBuyCarInfoService extends IService<InstallmentBuyCarInfo> {


    List<InstallmentBuyCarInfoVo> listAll();
}

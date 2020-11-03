package com.sw.chefubao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sw.chefubao.entity.InstallmentBuyCarInfo;
import com.sw.chefubao.mapper.InstallmentBuyCarInfoMapper;
import com.sw.chefubao.service.InstallmentBuyCarInfoService;
import com.sw.chefubao.vo.InstallmentBuyCarInfoVo;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class InstallmentBuyCarInfoServiceImpl extends ServiceImpl<InstallmentBuyCarInfoMapper, InstallmentBuyCarInfo> implements InstallmentBuyCarInfoService {

    @Override
    public List<InstallmentBuyCarInfoVo> listAll() {
        return baseMapper.listAll();
    }
}

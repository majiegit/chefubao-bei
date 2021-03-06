package com.sw.chefubao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sw.chefubao.entity.InstallmentBuyCarInfo;
import com.sw.chefubao.vo.InstallmentBuyCarInfoVo;

import java.util.List;

/**
 * @author mj
 * 分期买车提交信息
 */
public interface InstallmentBuyCarInfoMapper extends BaseMapper<InstallmentBuyCarInfo> {

    List<InstallmentBuyCarInfoVo> listAll();
}

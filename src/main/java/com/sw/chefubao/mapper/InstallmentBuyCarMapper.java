package com.sw.chefubao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sw.chefubao.entity.InstallmentBuyCar;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mj
 * 分期买车
 */
public interface InstallmentBuyCarMapper extends BaseMapper<InstallmentBuyCar> {

    List<InstallmentBuyCar> listForLimit(@Param("number") Integer number);

}

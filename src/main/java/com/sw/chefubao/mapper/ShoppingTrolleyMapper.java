package com.sw.chefubao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sw.chefubao.entity.DrivingLicense;
import com.sw.chefubao.entity.ShoppingTrolley;
import com.sw.chefubao.vo.ShoppingTrolleyVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mj
 * 购物车
 */
public interface ShoppingTrolleyMapper extends BaseMapper<ShoppingTrolley> {

    List<ShoppingTrolleyVo> listByUserId(@Param("userId") Integer userId);
}

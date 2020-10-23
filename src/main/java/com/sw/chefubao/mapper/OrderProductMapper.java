package com.sw.chefubao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sw.chefubao.entity.OrderProduct;
import com.sw.chefubao.vo.OrderProductVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mj
 * 订单商品
 */
public interface OrderProductMapper extends BaseMapper<OrderProduct> {

    List<OrderProductVo> listByOrderId(@Param("orderId") Integer orderId);
}

package com.sw.chefubao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sw.chefubao.entity.OrderTable;
import org.apache.ibatis.annotations.Param;

/**
 * @author mj
 * 订单
 */
public interface OrderTableMapper extends BaseMapper<OrderTable> {
    int updateOrderStatus(@Param("orderId") Integer orderId, @Param("status") Integer status);
}

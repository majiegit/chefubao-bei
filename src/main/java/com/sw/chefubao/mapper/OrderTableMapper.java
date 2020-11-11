package com.sw.chefubao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sw.chefubao.entity.OrderTable;
import com.sw.chefubao.vo.OrderTableVo;
import org.apache.ibatis.annotations.Param;

/**
 * @author mj
 * 订单
 */
public interface OrderTableMapper extends BaseMapper<OrderTable> {
    int updateOrderStatus(@Param("orderId") Integer orderId, @Param("status") Integer status);

    IPage<OrderTableVo> pageList(IPage<OrderTableVo> orderTableIPage,  @Param("status") Integer status);
}

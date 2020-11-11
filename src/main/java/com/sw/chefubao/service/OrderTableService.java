package com.sw.chefubao.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sw.chefubao.entity.OrderTable;
import com.sw.chefubao.vo.OrderTableVo;

public interface OrderTableService extends IService<OrderTable> {
    /**
     * 如果订单是未付款状态，删除订单
     *
     * @param id
     */
    void deleteByStatus(String id);

    /**
     * 取消订单
     *
     * @param id
     * @return
     */
    boolean deleteOrder(Integer id);

    IPage<OrderTableVo> pageList(IPage<OrderTableVo> orderTableIPage, Integer status);
}

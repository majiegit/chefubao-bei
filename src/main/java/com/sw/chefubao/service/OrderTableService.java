package com.sw.chefubao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sw.chefubao.entity.OrderTable;

public interface OrderTableService extends IService<OrderTable> {
    /**
     * 如果订单是未付款状态，删除订单
     * @param id
     */
    void deleteByStatus(String id);

    /**
     * 删除订单
     * @param id
     * @return
     */
    boolean deleteOrder(Integer id);

}

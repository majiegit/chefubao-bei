package com.sw.chefubao.common.listener;

import com.sw.chefubao.service.OrderTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
public class RedisExpiredListener implements MessageListener {
    @Resource
    private OrderTableService orderTableService;

    @Override
    public void onMessage(Message message, byte[] bytes) {
        String orderIdKey = message.toString();
        if (orderIdKey.contains("orderId:")) {
            String id = orderIdKey.substring(orderIdKey.lastIndexOf(":") + 1);
          //  orderTableService.deleteByStatus(id);
        }
    }

}
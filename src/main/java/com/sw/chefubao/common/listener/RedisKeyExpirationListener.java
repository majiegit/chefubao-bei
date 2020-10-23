package com.sw.chefubao.common.listener;

import com.sw.chefubao.service.OrderTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * @author mj
 * 订单未付款事件监听
 */
@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {
    @Autowired
    private OrderTableService orderTableService;
    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    /**
     * 针对redis数据失效事件，进行数据处理  付款超时 取消订单
     * @param message
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        String orderIdKey = message.toString();
        if (orderIdKey.contains("orderId_")) {
            String id = orderIdKey.substring(orderIdKey.lastIndexOf("_") + 1);
            orderTableService.deleteByStatus(id);
        }
    }
}
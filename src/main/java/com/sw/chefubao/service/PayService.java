package com.sw.chefubao.service;

import java.util.Map;

public interface PayService {
    Map wxPay(String spbill_create_ip, String code, Integer orderId);

    int updateOrderStatus(String orderId);
}

package com.sw.chefubao.service;

import java.util.Map;

public interface PayService {
    Map wxPay(String spbill_create_ip, String code, Integer orderId);
    Map wxPayPrice(String spbill_create_ip, String code, Double price);

    int updateOrderStatus(String orderId);
}

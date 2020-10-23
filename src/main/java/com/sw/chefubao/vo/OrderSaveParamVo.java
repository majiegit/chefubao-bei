package com.sw.chefubao.vo;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderSaveParamVo {
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 用户收货地址ID
     */
    private Integer receiverAddressId;
    /**
     * 订单总价
     */
    private Integer payTotal;
    /**
     * 订单商品集合
     */
    private List<OrderSaveParam> orderSaveParams;
}

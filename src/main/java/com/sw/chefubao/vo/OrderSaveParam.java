package com.sw.chefubao.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSaveParam {
    /**
     * 商品Id
     */
    private Integer productId;
    /**
     * 购买数量
     */
    private Integer buyNumber;
    /**
     * 商品小计
     */
    private Integer subtotal;
}

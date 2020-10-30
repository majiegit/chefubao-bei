package com.sw.chefubao.vo;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 订单VO
 *
 * @author MJ
 */
@Getter
@Setter
public class OrderVo {

    private Integer id;
    private String orderNo;
    private Integer orderReceiverAddressId;
    private Double payTotal;
    private Integer status;
    private List<OrderProductVo> products;

}

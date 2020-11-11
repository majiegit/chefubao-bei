package com.sw.chefubao.vo;


import lombok.Data;

import java.util.Date;

/**
 * 订单
 * @author MJ
 */
@Data
public class OrderTableVo {
    private Integer orderId;
    private String orderNo;
    private Integer payTotal;
    private Integer status;
    private Date createTime;
    private String consignee;
    private String phone;
    private String province;
    private String city;
    private String county;
    private String detailedAddress;

}

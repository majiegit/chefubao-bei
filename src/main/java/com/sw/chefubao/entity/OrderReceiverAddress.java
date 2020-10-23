package com.sw.chefubao.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 订单收货地址表
 *
 * @author MJ
 */
@Data
@TableName("order_receiver_address")
public class OrderReceiverAddress {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String consignee;
    private String phone;
    private String province;
    private String city;
    private String county;
    private String detailedAddress;
    private Integer status;

}

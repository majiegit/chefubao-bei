package com.sw.chefubao.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 订单
 * @author MJ
 */
@Data
@TableName("order_table")
public class OrderTable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String orderNo;
    private Integer userId;
    private Integer orderReceiverAddressId;
    private Integer payTotal;
    private Integer status;
    private Date createTime;

}

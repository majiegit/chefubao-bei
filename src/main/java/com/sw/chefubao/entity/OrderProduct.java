package com.sw.chefubao.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
/**
 * 订单商品关系
 * @author MJ
 */
@Data
@TableName("order_product")
public class OrderProduct {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer orderId;
    private Integer productId;
    private Integer buyNumber;
    private Integer subtotal;

}

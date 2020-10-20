package com.sw.chefubao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 购物车
 */
@TableName("shopping_trolley")
@Data
public class ShoppingTrolley {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer productNumber;
}

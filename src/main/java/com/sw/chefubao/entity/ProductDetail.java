package com.sw.chefubao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 商品详情
 */
@Data
@TableName("product_detail")
public class ProductDetail {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer productId;
    private String imgPath;
}

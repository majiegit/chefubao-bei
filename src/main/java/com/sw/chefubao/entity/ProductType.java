package com.sw.chefubao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * 商品分类
 */
@TableName("product_type")
@Data
public class ProductType {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String typeName;
    private Integer sort;

}

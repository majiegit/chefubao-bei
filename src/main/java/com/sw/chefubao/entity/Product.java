package com.sw.chefubao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 商品
 */
@TableName("product")
@Data
public class Product {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String imgPath;
    private String description;
    private Integer originalPrice;
    private Integer currentPrice;
    private Integer buyNum;
    private Integer productTypeId;
    private Date updateTime;
    private Integer isDelete;

}

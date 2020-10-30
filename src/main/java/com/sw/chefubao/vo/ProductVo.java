package com.sw.chefubao.vo;

import lombok.Data;

import java.util.Date;

/**
 * 商品Vo
 */
@Data
public class ProductVo {
    private Integer id;
    private String name;
    private String imgPath;
    private String description;
    private Double originalPrice;
    private Double currentPrice;
    private Integer buyNum;
    private Integer productTypeId;
    private Integer stock;
    private Date updateTime;
    private Integer isSoldOut;

}

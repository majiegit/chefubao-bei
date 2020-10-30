package com.sw.chefubao.vo;

import com.sw.chefubao.entity.ProductDetail;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 商品
 */
@Data
public class ProductDetailWebVo {
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
    private Integer isDelete;
    private List<ProductDetail> productDetailList;

}

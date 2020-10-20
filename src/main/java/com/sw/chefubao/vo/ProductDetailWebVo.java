package com.sw.chefubao.vo;

import com.sw.chefubao.entity.Product;
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
    private Integer originalPrice;
    private Integer currentPrice;
    private Integer buyNum;
    private Integer productTypeId;
    private Date updateTime;
    private Integer isDelete;
    private List<ProductDetail> productDetailList;

}

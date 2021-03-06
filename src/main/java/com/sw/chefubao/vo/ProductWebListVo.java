package com.sw.chefubao.vo;

import lombok.Data;


/**
 * 客户端商品列表VO
 */

@Data
public class ProductWebListVo {

    private Integer id;
    private String name;
    private String imgPath;
    private Double currentPrice;
    private Integer productTypeId;
    private Integer buyNum;
}

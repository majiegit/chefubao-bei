package com.sw.chefubao.vo;

import lombok.Data;

/**
 * 购物车Vo
 */
@Data
public class ShoppingTrolleyVo {
    private Integer id;
    private Integer productId;
    private Integer productNumber;
    private String name;
    private String imgPath;
    private String currentPrice;
    private Integer isSoldOut;
}

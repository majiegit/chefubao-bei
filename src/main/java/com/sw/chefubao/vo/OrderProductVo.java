package com.sw.chefubao.vo;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 订单商品VO
 */
@Getter
@Setter
public class OrderProductVo {
        private Integer id;
    private String name;
    private String imgPath;
    private String description;
    private Double originalPrice;
    private Double currentPrice;
    private Date updateTime;
    private Integer isSoldOut;
    private Integer buyNumber;
}

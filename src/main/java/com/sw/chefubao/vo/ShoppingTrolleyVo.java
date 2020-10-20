package com.sw.chefubao.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
}

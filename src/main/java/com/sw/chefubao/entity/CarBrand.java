package com.sw.chefubao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 车辆品牌
 */
@Data
@TableName("car_brand")
public class CarBrand {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String brandName;
    private Integer status;

}

package com.sw.chefubao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 分期买车
 */
@TableName("installment_buy_car")
@Data
public class InstallmentBuyCar {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String imgPath;
    private Date updateTime;

}

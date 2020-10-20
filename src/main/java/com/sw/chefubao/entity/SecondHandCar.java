package com.sw.chefubao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 二手车
 */
@Data
@TableName("second_hand_car")
public class SecondHandCar {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String brand;
    private String type;
    private String description;
    private String color;
    private Integer price;
    private String buyTime;
    private String carImgOneLocation;
    private String carImgTwoLocation;
    private Integer drivingLicenseId;
    private String contactName;
    private String contactPhone;
    private Integer serviceCharge;
    private Integer status;
    private Date updateTime;

}

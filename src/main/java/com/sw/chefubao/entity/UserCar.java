package com.sw.chefubao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户车辆
 */
@Data
@TableName("user_car")
public class UserCar {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer carTypeId;
    private String carTypeExplain;
    private String carMotorNum;
    private String carRackNum;
    private String carMotorRackExplain;
    private String brand;
    private String color;
    private String buyDate;
    private String carFront45Photo;
    private String carRear45Photo;
    private String carInvoicePhoto;
    private String carFootPhoto;
    private String carMotorPhoto;


}

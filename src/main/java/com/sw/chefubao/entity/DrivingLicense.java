package com.sw.chefubao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 行驶证
 */
@TableName("driving_license")
@Data
public class DrivingLicense {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private String title;
    private String stamp;
    private String carType;
    private Integer carTypeId;
    private String carNum;
    private String owner;
    private String address;
    private String carMotorNum;
    private String carRackNum;
    private String brand;
    private String color;
    private String buyTime;
    private String dateOfIssue;
    private String validity;


}

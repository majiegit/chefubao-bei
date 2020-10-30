package com.sw.chefubao.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 行驶证Vo
 */
@Getter
@Setter
public class DrivingLicenseVo {
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
    private Integer status;
    private Integer carNumStatus;
    private Integer carId;
}

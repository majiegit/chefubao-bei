package com.sw.chefubao.vo;


import lombok.Data;

import java.util.Date;

/**
 * 二手车VO
 */
@Data
public class SecondHandCarVo {
    private Integer id;
    private String brand;
    private String type;
    private String description;
    private String color;
    private Double price;
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

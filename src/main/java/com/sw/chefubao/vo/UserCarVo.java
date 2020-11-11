package com.sw.chefubao.vo;

import lombok.Data;

@Data
public class UserCarVo {
    private Integer id;
    private Integer userId;
    private Integer carTypeId;
    private String carTypeName;
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

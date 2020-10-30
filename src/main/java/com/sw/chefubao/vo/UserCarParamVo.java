package com.sw.chefubao.vo;

import lombok.Data;

@Data
public class UserCarParamVo {
    private Integer userId;
    private Integer carTypeId;
    private String carTypeExplain;
    private String carMotorNum;
    private String carRackNum;
    private String carMotorRackExplain;
    private String brand;
    private String color;
    private String buyDate;
}

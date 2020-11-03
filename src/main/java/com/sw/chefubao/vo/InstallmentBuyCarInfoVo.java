package com.sw.chefubao.vo;

import lombok.Data;

import java.util.Date;

/**
 * 分期买车提交信息
 */
@Data
public class InstallmentBuyCarInfoVo {
    private Integer id;
    private String name;
    private String phone;
    private String cardNo;
    private String explainInfo;
    private String installmentBuyCarName;
    private Date updateTime;
}

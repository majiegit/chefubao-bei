package com.sw.chefubao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 分期买车提交信息
 */
@TableName("installment_buy_car_info")
@Data
public class InstallmentBuyCarInfo {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String phone;
    private String cardNo;
    private String explainInfo;
    private Integer installmentBuyCarId;
    private Date updateTime;
}

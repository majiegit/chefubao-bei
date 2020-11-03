package com.sw.chefubao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户
 */
@Data
@TableName("user")
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String phone;
    private String openId;
    private String idCard;
    private String sex;
    private String address;
    private String detailedAddress;
    private String cardImgPositive;
    private String cardImgNegative;

}

package com.sw.chefubao.vo;

import lombok.Data;

/**
 * 个人用户VO
 */
@Data
public class UserPeopleVo {
    private Integer id;
    private String username;
    private String phone;
    private String idCard;
    private String sex;
    private String address;
    private String detailedAddress;
    private String cardImgPositive;
    private String cardImgNegative;
    private Integer userType;
}

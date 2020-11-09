package com.sw.chefubao.vo;

import lombok.Data;

/**
 * 企业用户VO
 */
@Data
public class UserAccountVo {
    private Integer id;
    private String username;
    private String password;
    private Integer userType;
    private Integer totalNumber;
    private Integer registerNumber;
}

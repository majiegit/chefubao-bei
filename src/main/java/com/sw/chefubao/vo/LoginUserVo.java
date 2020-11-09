package com.sw.chefubao.vo;

import lombok.Data;

@Data
public class LoginUserVo {
    private Integer userId;
    private Integer permissionId;
    private Integer regionId;
    private String name;
    private String permissionValue;
    private Integer type;
}

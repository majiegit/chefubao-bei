package com.sw.chefubao.vo;

import com.sw.chefubao.entity.UserCar;
import lombok.Data;

/**
 * 牌照申领信息Vo
 */
@Data
public class UserLicenseTagApplyInfoVo {
    private Integer id;
    private String username;
    private String phone;
    private String address;
    private String detailedAddress;
    private Integer mode;
    private Integer status;
    private UserCar userCar;
}

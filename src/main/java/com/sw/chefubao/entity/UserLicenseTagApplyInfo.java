package com.sw.chefubao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 牌照申领信息
 */
@Data
@TableName("user_license_tag_apply_info")
public class UserLicenseTagApplyInfo {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer carId;
    private String username;
    private String phone;
    private String address;
    private String detailedAddress;
    private Integer mode;
    private Integer status;
    private Integer serviceId;
    private String carNum;
    private String peopleCarPhoto;
    private String sysUser;
    private Date createTime;
}

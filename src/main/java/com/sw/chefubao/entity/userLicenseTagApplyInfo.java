package com.sw.chefubao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 拍照申领信息
 */
@Data
@TableName("user_license_tag_apply_info")
public class userLicenseTagApplyInfo {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer userCarId;
    private String username;
    private String phone;
    private String address;
    private String detailedAddress;
    private Integer serviceId;
    private Integer mode;
    private Integer status;

}

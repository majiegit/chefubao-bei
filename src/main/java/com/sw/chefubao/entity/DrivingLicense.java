package com.sw.chefubao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 行驶证
 */
@TableName("driving_license")
@Data
public class DrivingLicense {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer carId;
    private String stamp;
    private String title;
    private String dateOfIssue;
    private String validity;
    private String carNum;
    private Integer carNumStatus;
}

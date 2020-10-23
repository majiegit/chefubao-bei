package com.sw.chefubao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 服务点
 */
@TableName("service_site")
@Data
public class ServiceSite {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String address;
    private String photo;
    private String longitude;
    private String latitude;


}

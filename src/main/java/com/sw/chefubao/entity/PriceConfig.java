package com.sw.chefubao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 价格配置
 */
@Data
@TableName("price_config")
public class PriceConfig {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Double price;
    private String remark;
    private Date createTime;

}

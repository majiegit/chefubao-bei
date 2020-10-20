package com.sw.chefubao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 规章制度
 */
@TableName("rules_regulations")
@Data
public class RulesRegulations {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String content;
    private Date updateTime;
    private Integer isDelete;
}

package com.sw.chefubao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 说明
 */
@Data
@TableName("explain_table")
public class ExplainTable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String content;
    private Integer type;
    private Date addTime;

}

package com.sw.chefubao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 通知
 */
@Data
@TableName("notice")
public class Notice {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String content;
    private Date addTime;
    private Date endTime;
    private Integer duration;
    private Integer status;

}

package com.sw.chefubao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * 说明
 */
@Data
@TableName("index_table")
public class IndexTable {
    private Integer keyId;
    private String label;

}

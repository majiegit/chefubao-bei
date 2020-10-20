package com.sw.chefubao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 菜单
 */
@TableName("menu")
@Data
public class Menu {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String iconPath;
    private String iconUrl;
    private Integer sort;

}

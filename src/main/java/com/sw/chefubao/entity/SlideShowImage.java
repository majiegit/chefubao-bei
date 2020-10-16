package com.sw.chefubao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("slideshow_image")
public class SlideShowImage {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String imgName;
    private String imgUse;
    private String imgLocation;
    private String imgUrl;
    private String isDelete;
}

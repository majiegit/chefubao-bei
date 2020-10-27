package com.sw.chefubao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 车辆类型
 */
@Data
@TableName("car_type")
public class CarType {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String carTypeName;
    private Integer isSell;

}

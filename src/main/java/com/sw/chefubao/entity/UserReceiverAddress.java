package com.sw.chefubao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user_receiver_address")
public class UserReceiverAddress {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private String consignee;
    private String phone;
    private String province;
    private String city;
    private String county;
    private String address;
    private String detailedAddress;
    private Integer status;

}

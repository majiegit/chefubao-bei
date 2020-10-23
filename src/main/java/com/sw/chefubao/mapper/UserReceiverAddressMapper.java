package com.sw.chefubao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sw.chefubao.entity.UserReceiverAddress;
import org.apache.ibatis.annotations.Param;

/**
 * @author mj
 * 收货地址
 */
public interface UserReceiverAddressMapper extends BaseMapper<UserReceiverAddress> {

    int updateStatus(@Param("userId") Integer userId, @Param("status") Integer status);
}

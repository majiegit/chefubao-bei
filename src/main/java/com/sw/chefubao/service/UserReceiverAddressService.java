package com.sw.chefubao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sw.chefubao.entity.UserReceiverAddress;

public interface UserReceiverAddressService extends IService<UserReceiverAddress> {

    int updateStatus(Integer userId, Integer status);
}

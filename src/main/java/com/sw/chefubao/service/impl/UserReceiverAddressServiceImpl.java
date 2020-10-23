package com.sw.chefubao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sw.chefubao.entity.UserReceiverAddress;
import com.sw.chefubao.mapper.UserReceiverAddressMapper;
import com.sw.chefubao.service.UserReceiverAddressService;
import org.springframework.stereotype.Service;

@Service
public class UserReceiverAddressServiceImpl extends ServiceImpl<UserReceiverAddressMapper, UserReceiverAddress> implements UserReceiverAddressService {
    @Override
    public int updateStatus(Integer userId, Integer status) {
        return baseMapper.updateStatus(userId, status);
    }
}

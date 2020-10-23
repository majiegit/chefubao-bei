package com.sw.chefubao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sw.chefubao.entity.OrderReceiverAddress;
import com.sw.chefubao.mapper.OrderReceiverAddressMapper;
import com.sw.chefubao.service.OrderReceiverAddressService;
import org.springframework.stereotype.Service;

@Service
public class OrderReceiverAddressServiceImpl extends ServiceImpl<OrderReceiverAddressMapper, OrderReceiverAddress> implements OrderReceiverAddressService {
}

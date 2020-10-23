package com.sw.chefubao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sw.chefubao.entity.OrderProduct;
import com.sw.chefubao.mapper.OrderProductMapper;
import com.sw.chefubao.service.OrderProductService;
import com.sw.chefubao.vo.OrderProductVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProductServiceImpl extends ServiceImpl<OrderProductMapper, OrderProduct> implements OrderProductService {
    @Override
    public List<OrderProductVo> listByOrderId(Integer orderId) {
        List<OrderProductVo> productList = baseMapper.listByOrderId(orderId);
        return productList;
    }
}

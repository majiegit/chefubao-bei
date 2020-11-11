package com.sw.chefubao.service.impl;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sw.chefubao.common.enums.OrderStatusEnum;
import com.sw.chefubao.entity.OrderProduct;
import com.sw.chefubao.entity.OrderTable;
import com.sw.chefubao.entity.Product;
import com.sw.chefubao.mapper.OrderProductMapper;
import com.sw.chefubao.mapper.OrderReceiverAddressMapper;
import com.sw.chefubao.mapper.OrderTableMapper;
import com.sw.chefubao.service.OrderTableService;
import com.sw.chefubao.service.ProductService;
import com.sw.chefubao.vo.OrderTableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderTableServiceImpl extends ServiceImpl<OrderTableMapper, OrderTable> implements OrderTableService {
    @Autowired
    private OrderProductMapper orderProductMapper;
    @Autowired
    private OrderReceiverAddressMapper orderReceiverAddressMapper;
    @Autowired
    private ProductService productService;

    @Override
    public void deleteByStatus(String id) {
        OrderTable orderTable = new OrderTable();
        int orderId = NumberUtil.parseInt(id);
        orderTable.setId(orderId);
        orderTable.setStatus(OrderStatusEnum.ORDER_WAIT_PAY.getKey());
        QueryWrapper<OrderTable> queryWrapper = new QueryWrapper<>(orderTable);
        OrderTable order = baseMapper.selectOne(queryWrapper);
        if (ObjectUtil.isNotEmpty(order)) {
            // 如果订单是待付款状态  删除订单
            deleteOrder(orderId);
        }
    }

    @Override
    public boolean deleteOrder(Integer id) {
        // 取消订单关联商品 并加库存
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrderId(id);
        QueryWrapper<OrderProduct> orderProductQueryWrapper = new QueryWrapper<>(orderProduct);
        //加库存
        List<OrderProduct> orderProducts = orderProductMapper.selectList(orderProductQueryWrapper);
        orderProducts.forEach((item) -> {
            Product product = productService.getById(item.getProductId());
            orderProductMapper.updateAddStock(item.getBuyNumber(), product.getStock(), item.getProductId());
        });
        orderProductMapper.delete(orderProductQueryWrapper);
        // 删除订单关联收货地址
        OrderTable order = baseMapper.selectById(id);
        if (ObjectUtil.isNotEmpty(order)) {
            orderReceiverAddressMapper.deleteById(order.getOrderReceiverAddressId());
        }
        // 删除订单
        baseMapper.deleteById(id);
        return true;
    }

    @Override
    public IPage<OrderTableVo> pageList(IPage<OrderTableVo> orderTableIPage, Integer status) {
       return baseMapper.pageList(orderTableIPage, status);
    }

}

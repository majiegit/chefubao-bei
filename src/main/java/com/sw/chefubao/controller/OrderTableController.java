package com.sw.chefubao.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sw.chefubao.common.R;
import com.sw.chefubao.common.enums.OrderStatusEnum;
import com.sw.chefubao.common.finals.RedisKeyFinal;
import com.sw.chefubao.common.util.NumberUtils;
import com.sw.chefubao.common.util.RedisUtils;
import com.sw.chefubao.entity.OrderReceiverAddress;
import com.sw.chefubao.entity.OrderTable;
import com.sw.chefubao.entity.OrderProduct;
import com.sw.chefubao.entity.UserReceiverAddress;
import com.sw.chefubao.service.OrderProductService;
import com.sw.chefubao.service.OrderReceiverAddressService;
import com.sw.chefubao.service.OrderTableService;
import com.sw.chefubao.service.UserReceiverAddressService;
import com.sw.chefubao.vo.OrderProductVo;
import com.sw.chefubao.vo.OrderSaveParamVo;
import com.sw.chefubao.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/**
 * 订单
 */
@RestController
@RequestMapping("/order")
public class OrderTableController {

    @Autowired
    private OrderTableService orderService;
    @Autowired
    private OrderProductService orderProductService;
    @Autowired
    private OrderReceiverAddressService orderReceiverAddressService;
    @Autowired
    private UserReceiverAddressService userReceiverAddressService;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 根据订单状态查询用户下的不同状态下的订单
     *
     * @param userId
     * @param status
     * @return
     */
    @PostMapping("/listBySatus")
    public R listBySatus(@RequestParam("userId") Integer userId, @RequestParam("status") Integer status) {
        OrderTable order = new OrderTable();
        order.setUserId(userId);
        order.setStatus(status);
        QueryWrapper<OrderTable> queryWrapper = new QueryWrapper<>(order);
        List<OrderTable> list = orderService.list(queryWrapper);
        LinkedList<OrderVo> orderVos = new LinkedList<>();
        list.forEach((order1) -> {
            OrderVo orderVo = BeanUtil.toBean(order1, OrderVo.class);
            List<OrderProductVo> productList = orderProductService.listByOrderId(order1.getId());
            orderVo.setProducts(productList);
            orderVos.add(orderVo);
        });
        return R.SELECT_SUCCESS.data(orderVos);
    }

    /**
     * 订单信息提交
     *
     * @param orderSaveParamVo
     * @return
     */

    @PostMapping("/save")
    @Transactional
    public R saveOrder(@RequestBody OrderSaveParamVo orderSaveParamVo) {
        /** 保存订单收货地址信息**/
        UserReceiverAddress userReceiverAddress = userReceiverAddressService.getById(orderSaveParamVo.getReceiverAddressId());
        OrderReceiverAddress orderReceiverAddress = BeanUtil.toBean(userReceiverAddress, OrderReceiverAddress.class);
        orderReceiverAddress.setId(null);
        orderReceiverAddressService.save(orderReceiverAddress);
        /** 保存订单**/
        OrderTable orderTable = new OrderTable();
        // 随机生成订单号
        orderTable.setOrderNo(NumberUtils.getRandom());
        // 订单为待付款状态
        orderTable.setStatus(OrderStatusEnum.ORDER_WAIT_PAY.getKey());
        // 订单用户ID
        orderTable.setUserId(orderSaveParamVo.getUserId());
        // 订单收货地址信息ID
        orderTable.setOrderReceiverAddressId(orderReceiverAddress.getId());
        // 订单总价
        orderTable.setPayTotal(orderSaveParamVo.getPayTotal());
        // 订单创建时间
        orderTable.setCreateTime(new Date());
        orderService.save(orderTable);
        /**保存订单商品关系**/
        Integer id = orderTable.getId();
        orderSaveParamVo.getOrderSaveParams().forEach((item) -> {
            OrderProduct orderProduct = BeanUtil.toBean(item, OrderProduct.class);
            orderProduct.setOrderId(id);
            orderProductService.save(orderProduct);
        });
        /** 保存订单ID到redis  30 分钟不付款 订单取消**/
        redisUtils.set(RedisKeyFinal.ORDER_ID_DISABLED_KEY + id, id, 1800);
        return R.SAVE_SUCCESS;
    }

    /**
     * 删除订单
     *
     * @param id
     * @return
     */
    @PostMapping("/delete")
    @Transactional
    public R delete(@RequestParam("id") Integer id) {
        boolean delete = orderService.deleteOrder(id);
        if (!delete) {
            return R.DELETE_ERROR;
        }
        return R.DELETE_SUCCESS;
    }
}

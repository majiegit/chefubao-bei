package com.sw.chefubao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sw.chefubao.entity.OrderProduct;
import com.sw.chefubao.vo.OrderProductVo;

import java.util.List;

public interface OrderProductService extends IService<OrderProduct> {

    List<OrderProductVo> listByOrderId(Integer id);

}

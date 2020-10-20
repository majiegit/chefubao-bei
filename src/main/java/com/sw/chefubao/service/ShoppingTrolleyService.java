package com.sw.chefubao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sw.chefubao.entity.ShoppingTrolley;
import com.sw.chefubao.vo.ShoppingTrolleyVo;

import java.util.List;

public interface ShoppingTrolleyService extends IService<ShoppingTrolley> {

    ShoppingTrolley get(Integer userId, Integer productId);

    /**
     * 查询用户的购物车清单
     * @param userId
     * @return
     */
    List<ShoppingTrolleyVo> listByUserId(Integer userId);
}

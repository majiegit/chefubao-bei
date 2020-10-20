package com.sw.chefubao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sw.chefubao.entity.ShoppingTrolley;
import com.sw.chefubao.mapper.ShoppingTrolleyMapper;
import com.sw.chefubao.service.ShoppingTrolleyService;
import com.sw.chefubao.vo.ShoppingTrolleyVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingTrolleyServiceImpl extends ServiceImpl<ShoppingTrolleyMapper, ShoppingTrolley> implements ShoppingTrolleyService {
    @Override
    public ShoppingTrolley get(Integer userId, Integer productId) {
        ShoppingTrolley shoppingTrolley = new ShoppingTrolley();
        shoppingTrolley.setUserId(userId);
        shoppingTrolley.setProductId(productId);
        QueryWrapper<ShoppingTrolley> queryWrapper = new QueryWrapper<>(shoppingTrolley);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public  List<ShoppingTrolleyVo> listByUserId(Integer userId) {
        List<ShoppingTrolleyVo> shoppingTrolleyVo = baseMapper.listByUserId(userId);

        return shoppingTrolleyVo;
    }
}

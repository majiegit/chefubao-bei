package com.sw.chefubao.controller;

import cn.hutool.core.util.ObjectUtil;
import com.sw.chefubao.common.R;
import com.sw.chefubao.entity.ShoppingTrolley;
import com.sw.chefubao.service.ShoppingTrolleyService;
import com.sw.chefubao.vo.ShoppingTrolleyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 购物车
 */
@RestController
@RequestMapping("/shoppingTrolley")
public class ShoppingTrolleyController {

    @Autowired
    private ShoppingTrolleyService shoppingTrolleyService;

    /**
     * 查询用户下购物车清单
     *
     * @param userId
     * @return
     */
    @GetMapping("/list")
    public R listByType(@RequestParam("userId") Integer userId) {
        List<ShoppingTrolleyVo> shoppingTrolleyVo =  shoppingTrolleyService.listByUserId(userId);
       return R.SELECT_SUCCESS.data(shoppingTrolleyVo);
    }

    /**
     * 加入购物车
     *
     * @param userId
     * @param productId
     * @return
     */
    @GetMapping("/save")
    public R getByUserId(@RequestParam("userId") Integer userId, @RequestParam("productId") Integer productId) {
        ShoppingTrolley shoppingTrolley = shoppingTrolleyService.get(userId, productId);
        if (ObjectUtil.isNotEmpty(shoppingTrolley)) {
            shoppingTrolley.setProductNumber(shoppingTrolley.getProductNumber() + 1);
            shoppingTrolleyService.updateById(shoppingTrolley);
        } else {
            ShoppingTrolley shoppingTrolley1 = new ShoppingTrolley();
            shoppingTrolley1.setProductId(productId);
            shoppingTrolley1.setUserId(userId);
            shoppingTrolley1.setProductNumber(1);
            shoppingTrolleyService.save(shoppingTrolley1);
        }
        return R.SAVE_SUCCESS;
    }


    /**
     * 删除
     *
     * @param id
     * @return
     */
    /*@GetMapping("/delete")
/*    public R delete(@RequestParam("id") Integer id) {
        boolean b = drivingLicenseService.removeById(id);
        if (b) {
            return R.DELETE_SUCCESS;
        }
        return R.DELETE_ERROR;
    }*/
}

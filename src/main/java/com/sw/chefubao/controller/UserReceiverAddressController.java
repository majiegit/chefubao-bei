package com.sw.chefubao.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sw.chefubao.common.R;
import com.sw.chefubao.common.enums.UserReceiverAddressStatusEnum;
import com.sw.chefubao.entity.UserReceiverAddress;
import com.sw.chefubao.service.UserReceiverAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 收货地址
 */
@RestController
@RequestMapping("/userReceiverAddress")
public class UserReceiverAddressController {

    @Autowired
    private UserReceiverAddressService userReceiverAddressService;

    /**
     * 查询用户下所有收货地址
     *
     * @param userId
     * @return
     */
    @GetMapping("/listByUserId")
    public R listByType(@RequestParam("userId") Integer userId) {
        UserReceiverAddress UserReceiverAddress = new UserReceiverAddress();
        UserReceiverAddress.setUserId(userId);
        QueryWrapper<UserReceiverAddress> queryWrapper = new QueryWrapper<>(UserReceiverAddress);
        List<UserReceiverAddress> list = userReceiverAddressService.list(queryWrapper);
        return R.SELECT_SUCCESS.data(list);
    }

    /**
     * 查询用户默认收货地址或第一个地址
     *
     * @param userId
     * @return
     */
    @GetMapping("/getByUserId")
    public R getByType(@RequestParam("userId") Integer userId) {
        UserReceiverAddress userReceiverAddress = new UserReceiverAddress();
        userReceiverAddress.setUserId(userId);
        userReceiverAddress.setStatus(UserReceiverAddressStatusEnum.ADDRESS_DEFAULT.getKey());
        QueryWrapper<UserReceiverAddress> queryWrapper = new QueryWrapper<>(userReceiverAddress);
        UserReceiverAddress one = userReceiverAddressService.getOne(queryWrapper, true);
        if (ObjectUtil.isNotEmpty(one)) {

            return R.SELECT_SUCCESS.data(one);
        } else {
            QueryWrapper<UserReceiverAddress> queryWrapperOne = new QueryWrapper<>();
            List<UserReceiverAddress> list = userReceiverAddressService.list(queryWrapperOne.eq("user_id", userId));
            if (list.size() != 0) {
                return R.SELECT_SUCCESS.data(list.get(0));
            } else {
                return R.SELECT_SUCCESS.data(null);
            }
        }
    }

    /**
     * 保存 新增 or 修改
     *
     * @param userReceiverAddress
     * @return
     */
    @PostMapping("/save")
    @Transactional
    public R getByUserId(@RequestBody UserReceiverAddress userReceiverAddress) {
        if (ObjectUtil.isEmpty(userReceiverAddress.getStatus())) {
            // 如果为空  则设置不是默认地址
            userReceiverAddress.setStatus(UserReceiverAddressStatusEnum.ADDRESS_NOT_DEFAULT.getKey());
        }
        if (userReceiverAddress.getStatus() == 1) {
            userReceiverAddressService.updateStatus(userReceiverAddress.getUserId(), 0);
        }
        boolean b = userReceiverAddressService.saveOrUpdate(userReceiverAddress);
        if (!b) {
            return R.SAVE_ERROR;
        }
        return R.SAVE_SUCCESS;
    }

    /**
     * 单个设置默认地址
     *
     * @param id
     * @return
     */
    @PostMapping("/updateStatus")
    @Transactional
    public R getByUserId(@RequestParam("id") Integer id) {
        UserReceiverAddress userReceiverAddress = userReceiverAddressService.getById(id);
        userReceiverAddressService.updateStatus(userReceiverAddress.getUserId(), 0);
        userReceiverAddress.setStatus(1);
        boolean b = userReceiverAddressService.updateById(userReceiverAddress);
        if (!b) {
            return R.SAVE_ERROR;
        }
        return R.SAVE_SUCCESS;
    }

    /**
     * 删除收货地址
     *
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public R delete(@RequestParam("id") Integer id) {
        boolean b = userReceiverAddressService.removeById(id);
        if (b) {
            return R.DELETE_SUCCESS;
        }
        return R.DELETE_ERROR;
    }
}

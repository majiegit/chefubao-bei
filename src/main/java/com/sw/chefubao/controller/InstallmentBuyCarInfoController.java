package com.sw.chefubao.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sw.chefubao.common.R;
import com.sw.chefubao.entity.InstallmentBuyCarInfo;
import com.sw.chefubao.service.InstallmentBuyCarInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 分期买车信息
 */
@RestController
@RequestMapping("/installmentBuyCarInfo")
public class InstallmentBuyCarInfoController {

    @Autowired
    private InstallmentBuyCarInfoService installmentBuyCarInfoService;

    /**
     * 查询买车分期买车提交信息
     *
     * @return
     */
    @GetMapping("/list")
    public R list() {
        QueryWrapper<InstallmentBuyCarInfo> queryWrapper = new QueryWrapper<>();
        List<InstallmentBuyCarInfo> list = installmentBuyCarInfoService.list(queryWrapper.orderByDesc("update_time"));
        return R.SELECT_SUCCESS.data(list);
    }


    /**
     * @return
     */
    @PostMapping("/save")
    public R save(@RequestBody InstallmentBuyCarInfo installmentBuyCarInfo) {
        installmentBuyCarInfo.setUpdateTime(new Date());
        boolean save = installmentBuyCarInfoService.save(installmentBuyCarInfo);
        if (!save) {
            return R.SAVE_ERROR;
        }
        return R.SAVE_SUCCESS;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public R delete(@RequestParam("id") Integer id) {
        boolean b = installmentBuyCarInfoService.removeById(id);
        if (b) {
            return R.DELETE_SUCCESS;
        }
        return R.DELETE_ERROR;
    }
}

package com.sw.chefubao.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sw.chefubao.common.R;
import com.sw.chefubao.entity.RulesRegulations;
import com.sw.chefubao.service.RulesRegulationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 规章制度
 */
@RestController
@RequestMapping("/rulesRegulations")
public class RulesRegulationsController {

    @Autowired
    private RulesRegulationsService rulesRegulationsService;

    /**
     * 列表
     *
     * @param isDelete 是否启用 1 启用 0不启用
     * @return
     */
    @GetMapping("/list")
    public R list(Integer isDelete) {
        RulesRegulations rulesRegulations = new RulesRegulations();
        if (ObjectUtil.isNotEmpty(isDelete)) {
            rulesRegulations.setIsDelete(isDelete);
        }
        QueryWrapper<RulesRegulations> queryWrapper = new QueryWrapper<>(rulesRegulations);
        List<RulesRegulations> list = rulesRegulationsService.list(queryWrapper);
        return R.SELECT_SUCCESS.data(list);
    }

    /**
     * 保存   新增or 更新
     * @param rulesRegulations
     * @return
     */
    @PostMapping("/save")
    public R save(@RequestBody RulesRegulations rulesRegulations) {

        boolean b = rulesRegulationsService.saveOrUpdate(rulesRegulations);
        if (b) {
            return R.SAVE_SUCCESS;
        }
        return R.SAVE_ERROR;

    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public R delete(@RequestParam("id") Integer id) {
        boolean b = rulesRegulationsService.removeById(id);
        if (b) {
            return R.DELETE_SUCCESS;
        }
        return R.DELETE_ERROR;
    }
}

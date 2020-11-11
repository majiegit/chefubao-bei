package com.sw.chefubao.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sw.chefubao.common.R;
import com.sw.chefubao.entity.ExplainTable;
import com.sw.chefubao.service.ExplainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 说明
 */
@RestController
@RequestMapping("/explain")
public class ExplainController {
    @Autowired
    private ExplainService explainService;


    /**
     * 查询
     *
     * @return
     */
    @GetMapping("/list")
    public R list() {
        List<ExplainTable> list = explainService.list();
        return R.SELECT_SUCCESS.data(list);
    }

    /**
     * 查询
     *
     * @return
     */
    @GetMapping("/get")
    public R get(Integer type) {
        ExplainTable explain = new ExplainTable();
        explain.setType(type);
        QueryWrapper<ExplainTable> explainQueryWrapper = new QueryWrapper<>(explain);
        ExplainTable one = explainService.getOne(explainQueryWrapper);
        return R.SELECT_SUCCESS.data(one);
    }

    /**
     * 保存（save or update）
     *
     * @return
     */
    @PostMapping("/save")
    public R save(@RequestBody ExplainTable explain) {
        boolean save = explainService.saveOrUpdate(explain);
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
    @PostMapping("/delete")
    public R delete(@RequestParam("id") Integer id) {
        boolean b = explainService.removeById(id);
        if (b) {
            return R.DELETE_SUCCESS;
        }
        return R.DELETE_ERROR;
    }
}

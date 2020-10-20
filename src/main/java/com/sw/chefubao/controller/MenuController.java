package com.sw.chefubao.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sw.chefubao.common.R;
import com.sw.chefubao.entity.Menu;
import com.sw.chefubao.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 菜单列表排序
     *
     * @return
     */
    @GetMapping("/list")
    public R list() {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        List<Menu> list = menuService.list(queryWrapper.orderByAsc("sort"));
        return R.SELECT_SUCCESS.data(list);
    }

    /**
     * 保存
     * @param menu
     * @return
     */
    @PostMapping("/save")
    public R getByUserId(@RequestBody Menu menu) {
        boolean b = menuService.saveOrUpdate(menu);
        if (!b) {
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
        boolean b = menuService.removeById(id);
        if (b) {
            return R.DELETE_SUCCESS;
        }
        return R.DELETE_ERROR;
    }
}

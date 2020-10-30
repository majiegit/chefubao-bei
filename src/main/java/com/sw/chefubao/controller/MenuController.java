package com.sw.chefubao.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sw.chefubao.common.R;
import com.sw.chefubao.common.config.ApplicaTionYmlConfig;
import com.sw.chefubao.common.util.FileUtils;
import com.sw.chefubao.entity.Menu;
import com.sw.chefubao.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 菜单
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private ApplicaTionYmlConfig applicaTionYmlConfig;
    /**
     * Icon图上传路径
     */
    private final String filePath = "/menu/";

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
     *
     * @param id
     * @param name
     * @param iconUrl
     * @param sort
     * @param file
     * @return
     */

    @PostMapping("/save")
    public R getByUserId(Integer id, @RequestParam("name") String name, @RequestParam("iconUrl") String iconUrl,
                         @RequestParam("sort") Integer sort, MultipartFile file) {

        Menu menu = new Menu();
        if (file != null) {
            String imgLocaltion = applicaTionYmlConfig.getFilePath() + filePath;
            String fileName = FileUtils.upload(imgLocaltion, file);
            menu.setIconPath(imgLocaltion + fileName);
        }
        menu.setId(id);
        menu.setName(name);
        menu.setIconUrl(iconUrl);
        menu.setSort(sort);
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

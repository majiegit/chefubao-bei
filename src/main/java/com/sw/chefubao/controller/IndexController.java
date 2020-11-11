package com.sw.chefubao.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sw.chefubao.common.R;
import com.sw.chefubao.entity.IndexTable;
import com.sw.chefubao.service.IndexService;
import net.sf.jsqlparser.statement.create.table.Index;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;


/**
 * 说明
 */
@RestController
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private IndexService indexService;


    /**
     * 查询
     *
     * @return
     */
    @GetMapping("/list")
    public R list() {
        List<IndexTable> list = indexService.list();
        return R.SELECT_SUCCESS.data(list);
    }

    /**
     * 保存（save or update）
     *
     * @return
     */
    @PostMapping("/save")
    @Transactional
    public R save(@RequestBody List<Integer> ids) {
        LinkedList<IndexTable> indexLinkedList = new LinkedList<>();
        for (Integer id : ids) {
            QueryWrapper<IndexTable> objectQueryWrapper = new QueryWrapper<>();
            IndexTable byId = indexService.getOne(objectQueryWrapper.eq("key_id", id));
            IndexTable indexTable = new IndexTable();
            indexTable.setKeyId(id);
            indexTable.setLabel(byId.getLabel());
            indexLinkedList.add(indexTable);
            indexService.remove(objectQueryWrapper.eq("key_id", id));
        }

        boolean save = indexService.saveBatch(indexLinkedList);
        if (!save) {
            return R.SAVE_ERROR;
        }
        return R.SAVE_SUCCESS;
    }
}

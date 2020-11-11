package com.sw.chefubao.controller;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sw.chefubao.common.R;
import com.sw.chefubao.entity.Notice;
import com.sw.chefubao.service.NoticeService;
import com.sw.chefubao.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 车辆类型
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    /**
     * 查询列表
     *
     * @return
     */
    @GetMapping("/list")
    public R list(Integer status) {
        Notice notice = new Notice();
        notice.setStatus(status);
        QueryWrapper<Notice> noticeQueryWrapper = new QueryWrapper<>(notice);
        List<Notice> list = noticeService.list(noticeQueryWrapper);
        return R.SELECT_SUCCESS.data(list);
    }

    /**
     * 保存（save or update）
     *
     * @return
     */
    @PostMapping("/save")
    public R save(@RequestBody Notice notice) {
        if (ObjectUtil.isNotEmpty(notice.getId())) {
            Notice byId = noticeService.getById(notice.getId());
            Integer duration = notice.getDuration();
            Date addTime = byId.getAddTime();
            DateTime dateTime = DateUtil.offsetDay(addTime, duration);
            notice.setAddTime(addTime);
            notice.setEndTime(dateTime);
        } else {
            Date date = new Date();
            Integer duration = notice.getDuration();
            DateTime dateTime = DateUtil.offsetDay(date, duration);
            notice.setAddTime(date);
            notice.setEndTime(dateTime);
        }
        boolean save = noticeService.saveOrUpdate(notice);
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


        boolean b = noticeService.removeById(id);
        if (b) {
            return R.DELETE_SUCCESS;
        }
        return R.DELETE_ERROR;
    }
}

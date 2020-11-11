package com.sw.chefubao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sw.chefubao.entity.Notice;
import com.sw.chefubao.mapper.NoticeMapper;
import com.sw.chefubao.service.NoticeService;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {
    
}

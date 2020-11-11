package com.sw.chefubao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sw.chefubao.entity.IndexTable;
import com.sw.chefubao.mapper.IndexMapper;
import com.sw.chefubao.service.IndexService;
import org.springframework.stereotype.Service;

@Service
public class IndexServiceImpl extends ServiceImpl<IndexMapper, IndexTable> implements IndexService {
    
}

package com.sw.chefubao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sw.chefubao.entity.ServiceSite;
import com.sw.chefubao.mapper.ServiceSiteMapper;
import com.sw.chefubao.service.ServiceSiteService;
import org.springframework.stereotype.Service;

@Service
public class ServiceSiteServiceImpl extends ServiceImpl<ServiceSiteMapper, ServiceSite> implements ServiceSiteService {
}

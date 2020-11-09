package com.sw.chefubao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sw.chefubao.entity.SysUser;
import com.sw.chefubao.mapper.SysUserMapper;
import com.sw.chefubao.service.SysUserService;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {



    @Override
    public SysUser getByUserName(String username) {
        return baseMapper.getByUserName(username);
    }

}

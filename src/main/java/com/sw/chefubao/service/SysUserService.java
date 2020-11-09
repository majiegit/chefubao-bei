package com.sw.chefubao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sw.chefubao.entity.SysUser;

public interface SysUserService extends IService<SysUser> {


    SysUser getByUserName(String username);


}

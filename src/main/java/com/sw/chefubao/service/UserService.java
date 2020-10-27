package com.sw.chefubao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sw.chefubao.entity.User;

public interface UserService extends IService<User> {

    boolean updateCardFilePathPositive(String fileNameCardOne, Integer userId);
    boolean updateCardFilePathNegative(String fileNameCardOne, Integer userId);
}

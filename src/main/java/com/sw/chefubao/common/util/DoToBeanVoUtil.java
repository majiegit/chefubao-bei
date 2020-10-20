package com.sw.chefubao.common.util;

import cn.hutool.core.bean.BeanUtil;

import java.util.LinkedList;
import java.util.List;

public class DoToBeanVoUtil {
    /**
     * Do集合转换为Vo集合
     *
     * @param list
     * @param clazz
     * @param <T>
     * @return
     */
   public static <T> List<T> toList(List list, Class<T> clazz) {
        List<T> resultListVo = new LinkedList<>();
        list.forEach((item) -> {
            resultListVo.add(BeanUtil.toBean(item, clazz));
        });
        return resultListVo;
    }
}

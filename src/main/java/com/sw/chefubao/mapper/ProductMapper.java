package com.sw.chefubao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sw.chefubao.entity.Product;
import org.apache.ibatis.annotations.Param;

/**
 * @author mj
 * 商品
 */
public interface ProductMapper extends BaseMapper<Product> {

    Page<Product> pageList(Page<Product> page,@Param("productTypeId") Integer productTypeId);
}

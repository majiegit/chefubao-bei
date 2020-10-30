package com.sw.chefubao.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sw.chefubao.common.R;
import com.sw.chefubao.common.enums.ProductSoldOutEnum;
import com.sw.chefubao.common.util.DoToBeanVoUtil;
import com.sw.chefubao.entity.Product;
import com.sw.chefubao.entity.ProductDetail;
import com.sw.chefubao.service.ProductDetailService;
import com.sw.chefubao.service.ProductService;
import com.sw.chefubao.vo.ProductDetailWebVo;
import com.sw.chefubao.vo.ProductVo;
import com.sw.chefubao.vo.ProductWebListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

/**
 * 商品
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductDetailService productDetailService;

    /**
     * 查询全部商品
     *
     * @return
     */
    @GetMapping("/list")
    public R list() {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        List<Product> list = productService.list(queryWrapper.orderByDesc("update_time"));
        List<ProductWebListVo> resultListVo = new LinkedList<>();
        list.forEach((item) -> {
            ProductWebListVo productWebListVo = BeanUtil.toBean(item, ProductWebListVo.class);
            productWebListVo.setCurrentPrice(item.getCurrentPrice() * 0.01);
            resultListVo.add(productWebListVo);
        });
        return R.SELECT_SUCCESS.data(resultListVo);
    }

    /**
     * 根据类别查询在售商品列表
     *
     * @param productTypeId
     * @return
     */
    @GetMapping("/listByType")
    public R listByType(Integer productTypeId) {
        Product product = new Product();
        product.setIsSoldOut(ProductSoldOutEnum.NOT_SOLD_OUT.getKey());
        if (ObjectUtil.isNotEmpty(productTypeId)) {
            product.setProductTypeId(productTypeId);
        }
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>(product);
        List<Product> list = productService.list(queryWrapper.orderByDesc("update_time"));
        List<ProductWebListVo> resultListVo = new LinkedList<>();
        list.forEach((item) -> {
            ProductWebListVo productWebListVo = BeanUtil.toBean(item, ProductWebListVo.class);
            productWebListVo.setCurrentPrice(item.getCurrentPrice() * 0.01);
            resultListVo.add(productWebListVo);
        });
        return R.SELECT_SUCCESS.data(resultListVo);
    }

    /**
     * 根据商品名称模糊查询列表
     *
     * @param name
     * @return
     */
    @GetMapping("/listByName")
    public R listByName(@RequestParam("name") String name) {
        Product product = new Product();
        product.setIsSoldOut(ProductSoldOutEnum.NOT_SOLD_OUT.getKey());
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>(product);
        List<Product> list = productService.list(queryWrapper.like("name", name).orderByDesc("update_time"));
        List<ProductWebListVo> resultListVo = new LinkedList<>();
        list.forEach((item) -> {
            ProductWebListVo productWebListVo = BeanUtil.toBean(item, ProductWebListVo.class);
            productWebListVo.setCurrentPrice(item.getCurrentPrice() * 0.01);
            resultListVo.add(productWebListVo);
        });
        return R.SELECT_SUCCESS.data(resultListVo);
    }


    /**
     * 删除
     *
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public R delete(@RequestParam("id") Integer id) {
        boolean b = productService.removeById(id);
        if (b) {
            return R.DELETE_SUCCESS;
        }
        return R.DELETE_ERROR;
    }

    /**
     * 查询商品详细
     *
     * @param id
     * @return
     */
    @GetMapping("/getById")
    public R getById(@RequestParam("id") Integer id) {
        Product product = productService.getById(id);
        ProductDetail productDetail = new ProductDetail();
        productDetail.setProductId(id);
        QueryWrapper<ProductDetail> productDetailQueryWrapper = new QueryWrapper<>(productDetail);
        List<ProductDetail> productDetailList = productDetailService.list(productDetailQueryWrapper);
        ProductDetailWebVo productDetailWebVo = BeanUtil.toBean(product, ProductDetailWebVo.class);
        productDetailWebVo.setCurrentPrice(product.getCurrentPrice() * 0.01);
        productDetailWebVo.setOriginalPrice(product.getCurrentPrice() * 0.01);
        productDetailWebVo.setProductDetailList(productDetailList);
        return R.SELECT_SUCCESS.data(productDetailWebVo);
    }

    /**
     * ID查询商品
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    public R getByIdOne(@RequestParam("id") Integer id) {
        Product product = productService.getById(id);
        ProductVo productVo = BeanUtil.toBean(product, ProductVo.class);
        productVo.setCurrentPrice(product.getCurrentPrice() * 0.01);
        productVo.setOriginalPrice(product.getOriginalPrice() * 0.01);
        return R.SELECT_SUCCESS.data(productVo);
    }

}

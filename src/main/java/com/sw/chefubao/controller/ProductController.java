package com.sw.chefubao.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sw.chefubao.common.R;
import com.sw.chefubao.common.config.ApplicaTionYmlConfig;
import com.sw.chefubao.common.enums.ProductSoldOutEnum;
import com.sw.chefubao.common.util.FileUtils;
import com.sw.chefubao.entity.Product;
import com.sw.chefubao.entity.ProductDetail;
import com.sw.chefubao.service.ProductDetailService;
import com.sw.chefubao.service.ProductService;
import com.sw.chefubao.vo.ProductDetailWebVo;
import com.sw.chefubao.vo.ProductVo;
import com.sw.chefubao.vo.ProductWebListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;
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
    @Autowired
    private ApplicaTionYmlConfig applicaTionYmlConfig;
    /**
     * 商品图片/上传路径
     */
    private final String filePath = "/product/";

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
            BigDecimal round = NumberUtil.round(NumberUtil.mul(item.getCurrentPrice().intValue(), 0.01), 2);
            productWebListVo.setCurrentPrice(round.doubleValue());
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
            BigDecimal round = NumberUtil.round(NumberUtil.mul(item.getCurrentPrice().intValue(), 0.01), 2);
            productWebListVo.setCurrentPrice(round.doubleValue());
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
            productWebListVo.setCurrentPrice(NumberUtil.round(NumberUtil.mul(item.getCurrentPrice().intValue(), 0.01), 2).doubleValue());
            resultListVo.add(productWebListVo);
        });
        return R.SELECT_SUCCESS.data(resultListVo);
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
        productDetailWebVo.setCurrentPrice(NumberUtil.round(NumberUtil.mul(product.getCurrentPrice().intValue(), 0.01), 2).doubleValue());
        productDetailWebVo.setOriginalPrice(NumberUtil.round(NumberUtil.mul(product.getOriginalPrice().intValue(), 0.01), 2).doubleValue());
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
        productVo.setCurrentPrice(NumberUtil.round(NumberUtil.mul(product.getCurrentPrice().intValue(), 0.01), 2).doubleValue());
        productVo.setOriginalPrice(NumberUtil.round(NumberUtil.mul(product.getOriginalPrice().intValue(), 0.01), 2).doubleValue());
        return R.SELECT_SUCCESS.data(productVo);
    }

    /**
     * 商品分页
     *
     * @param page
     * @return
     */
    @GetMapping("/page")
    public R pageList(Page<Product> page, Integer productTypeId) {
        Page<Product> pageResult = productService.pageList(page, productTypeId);
        return R.SELECT_SUCCESS.data(pageResult);
    }


    /**
     * 商品上下架
     *
     * @param id
     * @return
     */
    @GetMapping("/soldOut")
    public R delete(@RequestParam("id") Integer id, @RequestParam("sold") Integer sold) {
        Product product = new Product();
        product.setIsSoldOut(sold);
        product.setId(id);
        boolean b = productService.updateById(product);
        if (b) {
            return R.DELETE_SUCCESS;
        }
        return R.DELETE_ERROR;
    }


    /**
     * 商品新增
     *
     * @return
     */
    @PostMapping("/insert")
    @Transactional
    public R insert(
            String name,
            String description,
            Double originalPrice,
            Double currentPrice,
            Integer productTypeId,
            Integer stock,
            MultipartFile file) {

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setCurrentPrice(NumberUtil.mul(NumberUtil.round(currentPrice, 2), 100).intValue());
        product.setOriginalPrice(NumberUtil.mul(NumberUtil.round(originalPrice, 2), 100).intValue());
        product.setProductTypeId(productTypeId);
        product.setStock(stock);
        product.setIsSoldOut(ProductSoldOutEnum.NOT_SOLD_OUT.getKey());
        product.setUpdateTime(new Date());

        // 上传商品图片显示
        if (file != null) {
            String imgLocaltion = applicaTionYmlConfig.getFilePath() + filePath;
            String fileName = FileUtils.upload(imgLocaltion, file);
            product.setImgPath(imgLocaltion + fileName);
        }
        //保存商品信息
        productService.save(product);
        return R.SAVE_SUCCESS.data(product);
    }

    /**
     * 商品修改
     *
     * @return
     */
    @PostMapping("/update")
    @Transactional
    public R update(
            Integer id,
            String name,
            String description,
            Double originalPrice,
            Double currentPrice,
            Integer productTypeId,
            Integer stock,
            MultipartFile file) {

        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setDescription(description);
        product.setCurrentPrice(NumberUtil.mul(NumberUtil.round(currentPrice, 2), 100).intValue());
        product.setOriginalPrice(NumberUtil.mul(NumberUtil.round(originalPrice, 2), 100).intValue());
        product.setProductTypeId(productTypeId);
        product.setStock(stock);
        product.setUpdateTime(new Date());
        // 上传商品图片显示
        if (file != null) {
            String imgLocaltion = applicaTionYmlConfig.getFilePath() + filePath;
            String fileName = FileUtils.upload(imgLocaltion, file);
            product.setImgPath(imgLocaltion + fileName);
        }
        //保存商品信息
        productService.updateById(product);

        return R.UPDATE_SUCCESS;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @GetMapping("/delete")
    @Transactional
    public R delete(@RequestParam("id") Integer id) {
        boolean b = productService.removeById(id);
        ProductDetail productDetail = new ProductDetail();
        productDetail.setProductId(id);
        QueryWrapper<ProductDetail> productDetailQueryWrapper = new QueryWrapper<>(productDetail);
        productDetailService.remove(productDetailQueryWrapper);
        if (b) {
            return R.DELETE_SUCCESS;
        }
        return R.DELETE_ERROR;
    }

    /**
     * 保存商品明细
     *
     * @param id
     * @param file
     * @return
     */
    @PostMapping("/saveDeitel")
    public R saveDeitel(Integer id, MultipartFile file) {
        // 上传商品明细图片 并保存
        if (file != null) {
            String imgLocaltion = applicaTionYmlConfig.getFilePath() + filePath;
            String fileName = FileUtils.upload(imgLocaltion, file);
            ProductDetail productDetai = new ProductDetail();
            productDetai.setProductId(id);
            productDetai.setImgPath(imgLocaltion + fileName);
            productDetailService.save(productDetai);
            return R.UPLOAD_SUCCESS;
        } else {
            return new R(506, "请上传商品详情图片");
        }
    }


    /**
     * 修改商品明细
     *
     * @param id
     * @param file
     * @return
     */
    @PostMapping("/updateDeitel")
    public R updateDeitel(Integer id, MultipartFile file) {
        // 上传商品明细图片 并保存
        if (file != null) {
            ProductDetail productDetaiParams = new ProductDetail();
            productDetaiParams.setProductId(id);
            QueryWrapper<ProductDetail> productDetailQueryWrapper = new QueryWrapper<>(productDetaiParams);
            productDetailService.remove(productDetailQueryWrapper);
            String imgLocaltion = applicaTionYmlConfig.getFilePath() + filePath;
            String fileName = FileUtils.upload(imgLocaltion, file);
            ProductDetail productDetai = new ProductDetail();
            productDetai.setProductId(id);
            productDetai.setImgPath(imgLocaltion + fileName);
            productDetailService.save(productDetai);
            return R.UPLOAD_SUCCESS;
        } else {
            return new R(506, "请上传商品详情图片");
        }
    }
}


package com.sw.chefubao.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sw.chefubao.common.R;
import com.sw.chefubao.common.config.ApplicaTionYmlConfig;
import com.sw.chefubao.common.util.FileUtils;
import com.sw.chefubao.entity.SlideShowImage;
import com.sw.chefubao.service.SlideShowImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * 轮播图
 */
@RestController
@RequestMapping("/slideShowImage")
public class SlideShowImageController {

    @Autowired
    private SlideShowImageService slideShowImageService;
    @Autowired
    private ApplicaTionYmlConfig applicaTionYmlConfig;
    /**
     * 录播图上传路径
     */
    private final String filePath = "/slideshowimg/";

    /**
     * 列表
     *
     * @param isDelete 是否启用 1 启用 0不启用
     * @return
     */
    @GetMapping("/list")
    public R list(Integer isDelete) {
        SlideShowImage slideShowImage = new SlideShowImage();
        if (ObjectUtil.isNotEmpty(isDelete)) {
            slideShowImage.setIsDelete(isDelete);
        }
        QueryWrapper<SlideShowImage> queryWrapper = new QueryWrapper<>(slideShowImage);
        List<SlideShowImage> list = slideShowImageService.list(queryWrapper.orderByDesc("add_time"));
        return R.SELECT_SUCCESS.data(list);
    }

    /**
     * 更新
     *
     * @param id
     * @param imgUse
     * @param isDelete
     * @return
     */
    @PostMapping("/update")
    public R save(@RequestParam("id") Integer id, @RequestParam("imgUse") String imgUse, @RequestParam("isDelete") Integer isDelete) {
        SlideShowImage slideShowImage = new SlideShowImage();
        slideShowImage.setId(id);
        slideShowImage.setImgUse(imgUse);
        slideShowImage.setIsDelete(isDelete);
        slideShowImage.setAddTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        boolean b = slideShowImageService.updateById(slideShowImage);
        if (b) {
            return R.UPDATE_SUCCESS;
        }
        return R.UPDATE_ERROR;

    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public R delete(@RequestParam("id") Integer id) {
        boolean b = slideShowImageService.removeById(id);
        if (b) {
            return R.DELETE_SUCCESS;
        }
        return R.DELETE_ERROR;
    }

    /**
     * 保存
     *
     * @return
     */
    @PostMapping("/save")
    public R uploading(Integer id, @RequestParam("imgName") String imgName,
                       @RequestParam("imgUse") String imgUse, @RequestParam("imgUrl") String imgUrl,
                       @RequestParam("isDelete") Integer isDelete, MultipartFile file) {

        SlideShowImage slideShowImage = new SlideShowImage();
        if (file != null) {
            String imgLocaltion = applicaTionYmlConfig.getFilePath() + filePath;
            String fileName = FileUtils.upload(imgLocaltion, file);
            slideShowImage.setImgLocation(imgLocaltion + fileName);
        }
        slideShowImage.setId(id);
        slideShowImage.setImgUse(imgUse);
        slideShowImage.setImgName(imgName);
        slideShowImage.setImgUrl(imgUrl);
        slideShowImage.setAddTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        slideShowImage.setIsDelete(isDelete);
        slideShowImageService.saveOrUpdate(slideShowImage);

        return R.UPLOAD_SUCCESS;
    }
}

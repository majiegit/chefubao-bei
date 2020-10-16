package com.sw.chefubao.controller;

import com.sw.chefubao.common.R;
import com.sw.chefubao.entity.SlideShowImage;
import com.sw.chefubao.service.SlideShowImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/slideShowImage")
public class SlideShowImageController {

    @Autowired
    private SlideShowImageService slideShowImageService;

    /**
     * 轮播图片列表
     * @return
     */
    @GetMapping("/list")
    public R list(){
        List<SlideShowImage> list = slideShowImageService.list();
        return R.SELECT_SUCCESS.data(list);
    }

}

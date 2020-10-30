package com.sw.chefubao.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sw.chefubao.common.R;
import com.sw.chefubao.common.config.ApplicaTionYmlConfig;
import com.sw.chefubao.common.finals.RedisKeyFinal;
import com.sw.chefubao.common.util.FileUtils;
import com.sw.chefubao.common.util.RedisUtils;
import com.sw.chefubao.entity.User;
import com.sw.chefubao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ApplicaTionYmlConfig applicaTionYmlConfig;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 身份证图片保存路径
     */
    private final String filePath = "/card/";

    /**
     * 根据手机号查询用户是否存在
     *
     * @param phone
     * @return
     */
    @PostMapping("/get")
    public R get(@RequestParam("phone") String phone) {
        User user = new User();
        user.setPhone(phone);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>(user);
        User one = userService.getOne(userQueryWrapper, true);
        if (ObjectUtil.isEmpty(one)) {
            return R.SELECT_USER_ERROR;
        }
        return R.SELECT_SUCCESS.data(one);
    }

    /**
     * 根据id
     *
     * @param id
     * @return
     */
    @PostMapping("/getById")
    public R get(@RequestParam("id") Integer id) {
        User one = userService.getById(id);
        if (ObjectUtil.isEmpty(one)) {
            return R.SELECT_USER_ERROR;
        }
        return R.SELECT_SUCCESS.data(one);
    }

    /**
     * 用户信息登记
     *
     * @param username
     * @param sex
     * @param idCard
     * @param address
     * @param detailedAddress
     * @return
     */
    @PostMapping("/save")
    public R save(@RequestParam("username") String username, @RequestParam("phone") String phone,
                  @RequestParam("username") String sex, @RequestParam("idCard") String idCard,
                  @RequestParam("address") String address, @RequestParam("detailedAddress") String detailedAddress,
                  @RequestParam("wehatUserId") String wechatUserId) {
        User user = new User();
        user.setUsername(username);
        user.setPhone(phone);
        user.setWechatUserId(wechatUserId);
        user.setSex(sex);
        user.setIdCard(idCard);
        user.setAddress(address);
        user.setDetailedAddress(detailedAddress);
        boolean save = userService.save(user);
        if (!save) {
            return R.SAVE_ERROR;
        }
        return R.SAVE_SUCCESS.data(user);
    }

    /**
     * 上传身份证正面照片
     *
     * @param userId
     * @param file
     * @return
     */
    @PostMapping("/upload/positive")
    public R uploadIdCardPositive(@RequestParam("userId") Integer userId, @RequestParam("file") MultipartFile file) {
        //上传车辆照片
        String imgPath = applicaTionYmlConfig.getFilePath() + filePath;
        String fileNameCardOne = FileUtils.upload(imgPath, file);
        boolean upload = userService.updateCardFilePathPositive(fileNameCardOne, userId);
        if (!upload) {
            return R.UPLOAD_ERROR;
        }
        return R.UPLOAD_SUCCESS;
    }

    /**
     * 上传身份证反面照片
     *
     * @param userId
     * @param file
     * @return
     */
    @PostMapping("/upload/negative")
    public R uploadIdCardNegative(@RequestParam("userId") Integer userId, @RequestParam("file") MultipartFile file) {
        //上传车辆照片
        String imgPath = applicaTionYmlConfig.getFilePath() + filePath;
        String fileNameCardOne = FileUtils.upload(imgPath, file);
        boolean upload = userService.updateCardFilePathNegative(fileNameCardOne, userId);
        if (!upload) {
            return R.UPLOAD_ERROR;
        }
        return R.UPLOAD_SUCCESS;
    }

    /**
     * 短信验证码发送接口
     *
     * @return
     */
    @PostMapping("/sendAuthCode")
    public R sendAuthCode(@RequestParam("phone") String phone) {
        // 短信验证码值    需接通阿里短信服务
        String authCode = "123456";
        String key = RedisKeyFinal.AUTH_CODE_KEY + phone;
        boolean exits = redisUtils.hasKey(key);
        if (exits) {
            redisUtils.del(key);
        }
        boolean set = redisUtils.set(key, authCode, 300);
        if (!set) {
            return R.SEND_ERROR;
        }

        return R.SEND_SUCCESS;
    }

    /**
     * 短信验证码校验接口
     */
    @PostMapping("/getAuthCode")
    public R getAuthCode(@RequestParam("phone") String phone, @RequestParam("authCode") String authCode) {
        String key = RedisKeyFinal.AUTH_CODE_KEY + phone;
        boolean exits = redisUtils.hasKey(key);
        if (!exits) {
            return R.AUTH_CODE_DISABLED_ERROR;
        } else {
            Object o = redisUtils.get(key);
            if (authCode.equals(o)) {
                return R.AUTH_CODE_SUCCESS;
            } else {
                return R.AUTH_CODE_ERROR;
            }
        }
    }
}

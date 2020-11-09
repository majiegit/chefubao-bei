package com.sw.chefubao.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sw.chefubao.common.R;
import com.sw.chefubao.common.config.ApplicaTionYmlConfig;
import com.sw.chefubao.common.config.WechatConfig;
import com.sw.chefubao.common.enums.UserTypeEnum;
import com.sw.chefubao.common.finals.RedisKeyFinal;
import com.sw.chefubao.common.util.FileUtils;
import com.sw.chefubao.common.util.RedisUtils;
import com.sw.chefubao.common.util.SmsSendUtil;
import com.sw.chefubao.entity.User;
import com.sw.chefubao.service.UserService;
import com.sw.chefubao.vo.UserAccountVo;
import com.sw.chefubao.vo.UserPeopleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Random;

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
    public R save(@RequestParam("id") Integer id, @RequestParam("username") String username,
                  @RequestParam("phone") String phone, @RequestParam("sex") String sex, @RequestParam("idCard") String idCard,
                  @RequestParam("address") String address, @RequestParam("detailedAddress") String detailedAddress) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPhone(phone);
        user.setSex(sex);
        user.setIdCard(idCard);
        user.setAddress(address);
        user.setDetailedAddress(detailedAddress);
        user.setUserType(UserTypeEnum.USER_PEOPLE.getKey());
        boolean save = userService.updateById(user);
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
        boolean upload = userService.updateCardFilePathPositive(imgPath + fileNameCardOne, userId);
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
        boolean upload = userService.updateCardFilePathNegative(imgPath + fileNameCardOne, userId);
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
        Random r = new Random();
        Integer random = r.nextInt(900000) + 100000;
        String authCode = random.toString();
        String result = SmsSendUtil.sendAuth(authCode,phone);
        JSONObject jsonObject = JSONUtil.parseObj(result);
        if (jsonObject.get("Message").toString().equals("OK")) {
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
        }else {
            return R.SEND_ERROR;
        }
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

    /**
     * 个人用户登陆
     *
     * @param code
     * @return
     */
    @PostMapping("/login")
    public R weChatLogin(@RequestParam("code") String code) {
        String appId = WechatConfig.appid;
        String secret = WechatConfig.secret;
        String url = WechatConfig.openid_url + "?appid=" + appId +
                "&secret=" + secret + "&js_code=" + code + "&grant_type=authorization_code";
        String result = HttpUtil.get(url);
        JSONObject jsonObject = JSONUtil.parseObj(result);
        System.out.print(result);
        String openId = jsonObject.get("openid").toString();
        User user = userService.selectUser(openId);
        User userParam = new User();
        if (ObjectUtil.isEmpty(user)) {
            // 没有用户则保存openId
            userParam.setOpenId(openId);
            userService.save(userParam);
            return R.SELECT_SUCCESS.data(userParam);
        } else {
            return R.SELECT_SUCCESS.data(user);
        }
    }

    /**
     * 企业账号登陆
     *
     * @param username
     * @param password
     * @return
     */

    @PostMapping("/companyLogin")
    public R weChatLogin(String username, String password) {
        if (ObjectUtil.isEmpty(username)) {
            return new R(506, "请输入用户名");
        }
        if (ObjectUtil.isEmpty(password)) {
            return new R(506, "请输入密码");
        }
        User user = new User();
        user.setUsername(username);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>(user);
        User one = userService.getOne(userQueryWrapper, true);
        if (ObjectUtil.isEmpty(one)) {
            return new R(506, "账号不存在");
        }
        if (!password.equals(one.getPassword())) {
            return new R(506, "密码输入错误,请重新输入");
        } else {
            return R.LOGIN_SUCCESS;
        }
    }
}

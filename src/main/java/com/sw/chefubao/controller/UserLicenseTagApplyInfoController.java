package com.sw.chefubao.controller;


import com.sw.chefubao.common.R;
import com.sw.chefubao.common.enums.UserLicenseTagApplyInfoModeEnum;
import com.sw.chefubao.common.enums.UserLicenseTagApplyInfoStatusEnum;
import com.sw.chefubao.entity.UserLicenseTagApplyInfo;
import com.sw.chefubao.service.UserLicenseTagApplyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 申领信息
 */
@RestController
@RequestMapping("/applyInfo")
public class UserLicenseTagApplyInfoController {
    @Autowired
    private UserLicenseTagApplyInfoService userLicenseTagApplyInfoService;


    /**
     * 邮寄方式保存
     *
     * @return
     */
    @PostMapping("/saveMail")
    public R saveMail(@RequestParam("userId") Integer userId, @RequestParam("userCarId") Integer userCarId,
                      @RequestParam("username") String username, @RequestParam("phone") String phone,
                      @RequestParam("address") String address, @RequestParam("detailedAddress") String detailedAddress) {
        UserLicenseTagApplyInfo userLicenseTagApplyInfo = new UserLicenseTagApplyInfo();
        userLicenseTagApplyInfo.setUserCarId(userCarId);
        userLicenseTagApplyInfo.setUserId(userId);
        userLicenseTagApplyInfo.setUsername(username);
        userLicenseTagApplyInfo.setPhone(phone);
        userLicenseTagApplyInfo.setAddress(address);
        userLicenseTagApplyInfo.setAddress(detailedAddress);
        userLicenseTagApplyInfo.setMode(UserLicenseTagApplyInfoModeEnum.MODE_MAIL.getKey());
        userLicenseTagApplyInfo.setStatus(UserLicenseTagApplyInfoStatusEnum.APPLY_STATUS_NOT.getKey());
        boolean save = userLicenseTagApplyInfoService.save(userLicenseTagApplyInfo);
        if (!save) {
            return R.SAVE_ERROR;
        }
        return R.SAVE_SUCCESS;
    }

    /**
     * 服务点保存
     *
     * @return
     */
    @PostMapping("/saveService")
    public R saveService(@RequestParam("userId") Integer userId, @RequestParam("userCarId") Integer userCarId, @RequestParam("serviceId") Integer serviceId) {
        UserLicenseTagApplyInfo userLicenseTagApplyInfo = new UserLicenseTagApplyInfo();
        userLicenseTagApplyInfo.setUserCarId(userCarId);
        userLicenseTagApplyInfo.setUserId(userId);
        userLicenseTagApplyInfo.setServiceId(serviceId);
        userLicenseTagApplyInfo.setMode(UserLicenseTagApplyInfoModeEnum.MODE_SERVICE.getKey());
        userLicenseTagApplyInfo.setStatus(UserLicenseTagApplyInfoStatusEnum.APPLY_STATUS_NOT.getKey());
        boolean save = userLicenseTagApplyInfoService.save(userLicenseTagApplyInfo);
        if (!save) {
            return R.SAVE_ERROR;
        }
        return R.SAVE_SUCCESS;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public R delete(@RequestParam("id") Integer id) {
        boolean b = userLicenseTagApplyInfoService.removeById(id);
        if (b) {
            return R.DELETE_SUCCESS;
        }
        return R.DELETE_ERROR;
    }
}

package com.sw.chefubao.common.enums;

/**
 * 申领信息车牌领取方式
 * @author MJ
 */
public enum UserLicenseTagApplyInfoModeEnum {
    MODE_SERVICE(0,"服务点方式"),

    MODE_MAIL(1,"邮寄方式");

    private Integer key;
    private String desc;

    UserLicenseTagApplyInfoModeEnum(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public Integer getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }

    public static UserLicenseTagApplyInfoModeEnum getAppStatus(Integer key) {
        for (UserLicenseTagApplyInfoModeEnum status : UserLicenseTagApplyInfoModeEnum.values()) {
            if (status.key.equals(key)) {
                return status;
            }
        }
        return null;
    }
}

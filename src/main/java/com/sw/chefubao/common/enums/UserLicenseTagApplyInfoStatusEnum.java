package com.sw.chefubao.common.enums;

/**
 * 申领信息状态
 *
 * @author MJ
 */
public enum UserLicenseTagApplyInfoStatusEnum {
    APPLY_STATUS_NOT(0, "未办理"),
    APPLY_STATUS_SUCCESS(1, "已办理");


    private Integer key;
    private String desc;

    UserLicenseTagApplyInfoStatusEnum(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public Integer getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }

    public static UserLicenseTagApplyInfoStatusEnum getAppStatus(Integer key) {
        for (UserLicenseTagApplyInfoStatusEnum status : UserLicenseTagApplyInfoStatusEnum.values()) {
            if (status.key.equals(key)) {
                return status;
            }
        }
        return null;
    }
}

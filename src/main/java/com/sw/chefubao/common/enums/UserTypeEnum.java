package com.sw.chefubao.common.enums;

/**
 * 用户类型
 * @author MJ
 */
public enum UserTypeEnum {
    USER_PEOPLE(0,"个人用户"),

    USER_ACCOUNT(1,"企业用户");

    private Integer key;
    private String desc;

    UserTypeEnum(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public Integer getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }

    public static UserTypeEnum getAppStatus(Integer key) {
        for (UserTypeEnum status : UserTypeEnum.values()) {
            if (status.key.equals(key)) {
                return status;
            }
        }
        return null;
    }
}

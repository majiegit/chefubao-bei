package com.sw.chefubao.common.enums;

/**
 * 用户收货地址状态
 * @author MJ
 */
public enum  UserReceiverAddressStatusEnum {
    ADDRESS_NOT_DEFAULT("0","非默认地址"),

    ADDRESS_DEFAULT("1","默认地址");

    private String key;
    private String desc;

    UserReceiverAddressStatusEnum(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }

    public static UserReceiverAddressStatusEnum getAppStatus(String key) {
        for (UserReceiverAddressStatusEnum status : UserReceiverAddressStatusEnum.values()) {
            if (status.key.equals(key)) {
                return status;
            }
        }
        return null;
    }
}

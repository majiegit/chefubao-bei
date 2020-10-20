package com.sw.chefubao.common.enums;

/**
 * 二手车销售状态枚举类
 *
 * @author MJ
 */
public enum SecondHandCarStatusEnum {
    ON_SOLD(0, "在售"),
    OUT_SOLD(1, "已售");
    private Integer key;
    private String desc;

    SecondHandCarStatusEnum(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public Integer getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }

    public static SecondHandCarStatusEnum getAppStatus(Integer key) {
        for (SecondHandCarStatusEnum status : SecondHandCarStatusEnum.values()) {
            if (status.key.equals(key)) {
                return status;
            }
        }
        return null;
    }
}

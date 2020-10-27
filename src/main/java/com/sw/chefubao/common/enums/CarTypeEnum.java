package com.sw.chefubao.common.enums;

/**
 * 车辆类型枚举类
 *
 * @author MJ
 */
public enum CarTypeEnum {
    FORBID_SOLD(3, "禁止出售"),
    ALLOW_SOLD(4, "允许出售");
    private Integer key;
    private String desc;

    CarTypeEnum(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public Integer getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }

    public static CarTypeEnum getAppStatus(Integer key) {
        for (CarTypeEnum status : CarTypeEnum.values()) {
            if (status.key.equals(key)) {
                return status;
            }
        }
        return null;
    }
}

package com.sw.chefubao.common.enums;

/**
 * 车辆类型枚举类
 *
 * @author MJ
 */
public enum DrivingLicenceEnum {
    CAR_NUM_NOT(0, "未绑定车牌"),
    CAR_NUM_OK(1, "已绑定车牌");
    private Integer key;
    private String desc;
    DrivingLicenceEnum(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public Integer getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }

    public static DrivingLicenceEnum getAppStatus(Integer key) {
        for (DrivingLicenceEnum status : DrivingLicenceEnum.values()) {
            if (status.key.equals(key)) {
                return status;
            }
        }
        return null;
    }
}

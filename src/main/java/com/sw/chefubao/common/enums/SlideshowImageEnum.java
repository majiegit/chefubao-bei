package com.sw.chefubao.common.enums;

/**
 * 轮播图状态
 *
 * @author MJ
 */
public enum SlideshowImageEnum {
    IMAGE_USEING(1, "启用"),

    IMAGE_NOT_USEING(0, "不启用");

    private Integer key;
    private String desc;

    SlideshowImageEnum(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public Integer getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }

    public static SlideshowImageEnum getAppStatus(Integer key) {
        for (SlideshowImageEnum status : SlideshowImageEnum.values()) {
            if (status.key.equals(key)) {
                return status;
            }
        }
        return null;
    }
}

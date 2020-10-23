package com.sw.chefubao.common.enums;

/**
 * 商品下架枚举
 *
 * @author MJ
 */
public enum ProductSoldOutEnum {
    NOT_SOLD_OUT(0, "在售商品"),
    SOLD_OUT(1, "已下架");
    private Integer key;
    private String desc;

    ProductSoldOutEnum(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public Integer getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }

    public static ProductSoldOutEnum getAppStatus(Integer key) {
        for (ProductSoldOutEnum status : ProductSoldOutEnum.values()) {
            if (status.key.equals(key)) {
                return status;
            }
        }
        return null;
    }
}

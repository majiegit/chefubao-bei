package com.sw.chefubao.common.enums;

/**
 * 商品分类枚举
 *
 * @author MJ
 */
public enum ProductTypeEnum {
    ORDER_COMPLETED(1, "护具"),
    ORDER_WAIT_PAY(2, "保暖"),
    ORDER_WAIT_FOR_SHIPMENTS(3, "灯具"),
    ORDER_WAIT_FOR_RECEIVING(4, "零件");
    private Integer key;
    private String desc;

    ProductTypeEnum(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public Integer getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }

    public static ProductTypeEnum getAppStatus(Integer key) {
        for (ProductTypeEnum status : ProductTypeEnum.values()) {
            if (status.key.equals(key)) {
                return status;
            }
        }
        return null;
    }
}

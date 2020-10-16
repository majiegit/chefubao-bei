package com.sw.chefubao.common.enums;

/**
 * 订单状态枚举类
 *
 * @author MJ
 */
public enum OrderStatusEnum {
    ORDER_COMPLETED("1", "已完成"),
    ORDER_WAIT_PAY("2", "待付款"),
    ORDER_WAIT_FOR_SHIPMENTS("3", "待发货"),
    ORDER_WAIT_FOR_RECEIVING("4", "待收货");
    private String key;
    private String desc;

    OrderStatusEnum(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }

    public static OrderStatusEnum getAppStatus(String key) {
        for (OrderStatusEnum status : OrderStatusEnum.values()) {
            if (status.key.equals(key)) {
                return status;
            }
        }
        return null;
    }
}

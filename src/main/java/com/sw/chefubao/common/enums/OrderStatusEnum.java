package com.sw.chefubao.common.enums;

/**
 * 订单状态枚举类
 *
 * @author MJ
 */
public enum OrderStatusEnum {
    ORDER_COMPLETED(1, "已完成"),
    ORDER_WAIT_PAY(2, "待付款"),
    ORDER_WAIT_FOR_SHIPMENTS(3, "待发货"),
    ORDER_WAIT_FOR_RECEIVING(4, "待收货");
    private Integer key;
    private String desc;

    OrderStatusEnum(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public Integer getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }

    public static OrderStatusEnum getAppStatus(Integer key) {
        for (OrderStatusEnum status : OrderStatusEnum.values()) {
            if (status.key.equals(key)) {
                return status;
            }
        }
        return null;
    }
}

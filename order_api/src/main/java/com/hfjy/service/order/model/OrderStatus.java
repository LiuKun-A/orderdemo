package com.hfjy.service.order.model;

/**
 * Created by HFJY on 2017/7/6.
 */
public enum OrderStatus {
    CREATED((byte)1, "创建"), PAY_SUCCESS((byte)2, "支付成功"), EXPIRED((byte)3, "过期"), DELETED((byte)0, "删除");

    private Byte value;
    private String name;

    OrderStatus(Byte value, String name) {
        this.value = value;
        this.name = name;
    }

    public Byte getValue() {
        return value;
    }
}

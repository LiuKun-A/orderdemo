package com.hfjy.service.order.vo;

import com.hfjy.service.order.model.Order;
import com.hfjy.service.order.model.OrderItem;
import com.hfjy.service.order.model.Payment;

import java.util.List;

/**
 * Created by HFJY on 2017/7/6.
 */
public class OrderInfo {
    private Order baseInfo;
    private List<Payment> payments;
    private List<OrderItem> items;

    public Order getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(Order baseInfo) {
        this.baseInfo = baseInfo;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}

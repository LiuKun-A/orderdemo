package com.hfjy.service.order.service;

import com.hfjy.service.order.dao.OrderItemMapper;
import com.hfjy.service.order.dao.OrderMapper;
import com.hfjy.service.order.dao.PaymentMapper;
import com.hfjy.service.order.model.Order;
import com.hfjy.service.order.model.OrderItem;
import com.hfjy.service.order.model.OrderStatus;
import com.hfjy.service.order.model.Payment;
import com.hfjy.service.order.vo.OrderInfo;
import com.hfjy.service.order.vo.PaymentUpdateStatusParam;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by HFJY on 2017/7/4.
 */
@Service
public class OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderItemMapper orderItemMapper;
    @Resource
    private PaymentMapper paymentMapper;

    public OrderInfo getByPrimaryKey(Long id) {
        OrderInfo orderInfo = new OrderInfo();
        Order order = orderMapper.selectByPrimaryKey(id);
        orderInfo.setBaseInfo(order);
        orderInfo.setItems(orderItemMapper.getByOrderSn(order.getOrderSn()));
        orderInfo.setPayments(paymentMapper.getByOrderSnAndStatus(order.getOrderSn(), null));
        return orderInfo;
    }

    public OrderInfo save(OrderInfo orderInfo) {
        Order order = orderInfo.getBaseInfo();
        orderMapper.insert(order);
        List<OrderItem> items = orderInfo.getItems();
        if (items != null && items.size() > 0) {
            for (OrderItem item : items) {
                item.setOrderId(order.getId());
                item.setOrderSn(order.getOrderSn());
            }
            orderItemMapper.batchInsert(items);
        }
        List<Payment> payments = orderInfo.getPayments();
        if (payments != null && payments.size() > 0) {
            for (Payment payment : payments) {
                payment.setOrderSn(order.getOrderSn());
                if (payment.getStatus() == null) {
                    payment.setStatus(1);
                }
            }
            paymentMapper.batchInsert(payments);
        }
        return orderInfo;
    }

    public Boolean deleteOrder(Long id) {
        return orderMapper.updateStatusByPrimaryKey(id, OrderStatus.DELETED.getValue()) == 1 ? Boolean.TRUE : Boolean.FALSE;
    }


    public Boolean updatePaymentStatus(Long id, PaymentUpdateStatusParam param) {
        return paymentMapper.updateStatusById(id, param.getStatus()) == 1 ? Boolean.TRUE : Boolean.FALSE;
    }
}

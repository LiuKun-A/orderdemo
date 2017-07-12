package com.hfjy.service.order.controller;

import com.hfjy.service.order.service.OrderService;
import com.hfjy.service.order.vo.OrderByUserIdParam;
import com.hfjy.service.order.vo.OrderInfo;
import com.hfjy.service.order.vo.PaymentUpdateStatusParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("/{id}")
    public OrderInfo getOrderById(@PathVariable("id") Long id) {
        return orderService.getByPrimaryKey(id);
    }

    @GetMapping("/getByUserId")
    public List<OrderInfo> getByUserId(OrderByUserIdParam param) {
        List<OrderInfo> result = null;
        return result;
    }

    @PostMapping("/save")
    public OrderInfo saveOrderInfo(@RequestBody OrderInfo orderInfo) {
        return orderService.save(orderInfo);
    }

    /**
     * 校验功能demo
     *
     * @param id
     * @param param
     * @return
     */
    @PutMapping(value = "/payment/{id}/status")
    public Boolean updatePaymentStatus(@PathVariable("id") Long id, @Validated @RequestBody PaymentUpdateStatusParam param) {
        return orderService.updatePaymentStatus(id, param);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteOrder(@PathVariable("id") Long id) {
        return orderService.deleteOrder(id);
    }
}

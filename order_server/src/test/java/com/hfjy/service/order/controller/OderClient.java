package com.hfjy.service.order.controller;

import com.alibaba.fastjson.JSON;
import com.hfjy.service.order.model.Order;
import com.hfjy.service.order.model.OrderItem;
import com.hfjy.service.order.model.Payment;
import com.hfjy.service.order.util.OrderIdUtil;
import com.hfjy.service.order.vo.OrderInfo;
import com.hfjy.service.order.vo.PaymentUpdateStatusParam;
import okhttp3.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by HFJY on 2017/7/7.
 */
public class OderClient {
    private static final String host="http://localhost:8000/order";
    private OkHttpClient client;

    @BeforeClass
    public void setup() {
        client = new OkHttpClient();
    }

    @Test
    public void testGetOrderById() throws IOException{
        Long id = 1L;
        Request request = new Request.Builder().url(host + "/" + id).build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

    @Test
    public void testSave() throws Exception {
        OrderInfo orderInfo = getOrderInfo();
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSON.toJSONString(orderInfo));
        Request request = new Request.Builder().url(host + "/save").post(body).build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

    private OrderInfo getOrderInfo() {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setBaseInfo(getOrder());
        orderInfo.setPayments(getPayments());
        orderInfo.setItems(getOrderItems());
        return orderInfo;
    }

    private Order getOrder() {
        Order order = new Order();
        order.setOrderSn(OrderIdUtil.generate(1000L));
        Random random = new Random(System.currentTimeMillis());
        int amount = random.nextInt(10000000);
        order.setPaymentAmount(new Long(amount));
        order.setProduceAmount(new Long(amount));
        order.setStatus((byte)1);
        order.setUserId(1000L);
        return order;
    }

    private List<OrderItem> getOrderItems() {
        List<OrderItem> result = new ArrayList<>(5);
        for (int i : IntStream.range(1,5).toArray()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setItemNumber(i);
            orderItem.setName("test" + i);
            orderItem.setPrice(i * 1000L);
            Random random = new Random(System.currentTimeMillis() + (int)(Math.random() * 1000));
            orderItem.setProduceId(random.nextLong());
            result.add(orderItem);
        }
        return result;
    }

    private List<Payment> getPayments() {
        List<Payment> result = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            Payment payment = new Payment();
            payment.setPayChannel((byte)i);
            payment.setSource((byte)(i+50));
            payment.setPaySum(0L + (int)(Math.random() * 100000));
            result.add(payment);
        }
        return result;
    }

    @Test
    public void testUpdatePaymentStatus() throws IOException{
        Long id = 1L;
        PaymentUpdateStatusParam param = new PaymentUpdateStatusParam();
        param.setStatus(9);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSON.toJSONString(param).getBytes());
        Request request = new Request.Builder().url(host + "/payment/" + id + "/status").put(body).build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

    @Test
    public void testDeleteOrder() throws Exception {
        Long id = 1L;
        Request request = new Request.Builder().url(host + "/" + id).delete().build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }
}

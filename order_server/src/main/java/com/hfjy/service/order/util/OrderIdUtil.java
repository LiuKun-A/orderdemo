package com.hfjy.service.order.util;

import org.joda.time.DateTime;

import java.util.Random;

/**
 * Created by HFJY on 2017/7/4.
 */
public class OrderIdUtil {
    public static String generate(Long userId) {
        Random random = new Random(System.currentTimeMillis());
        DateTime dateTime = new DateTime();
        String prefix = dateTime.toString("YYMMDDHHmmss");
        prefix += userId.toString() + random.nextInt(1000);
        return prefix;
    }
}

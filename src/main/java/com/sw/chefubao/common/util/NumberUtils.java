package com.sw.chefubao.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class NumberUtils {
    public static String getRandom() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate = sdf.format(new Date());
        String result = "";
        String result2 = "";
        Random random = new Random();
        for (int i = 0; i < 2; i++) {
            result += random.nextInt(10);
        }
        for (int i = 0; i < 3; i++) {
            result2 += random.nextInt(10);
        }
        String randomString = "130" + result + newDate + result2;
        return randomString;
    }
}

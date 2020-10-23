package com.sw.chefubao.controller;

import cn.hutool.core.util.NumberUtil;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GlobalCoordinates;
import org.junit.Test;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


@RestController
public class TestController {


    @Test
    public void test() {
        String orderIdKey = "orderId:1";
        String id = orderIdKey.substring(orderIdKey.lastIndexOf(":") + 1);
        System.out.print(id);
        //   double meter1 = getDistanceMeter(source, target, Ellipsoid.Sphere);
        //  double meter2 = getDistanceMeter(source, target, Ellipsoid.WGS84);

        //  System.out.println("Sphere坐标系计算结果："+meter1 + "米");
        //    System.out.println("WGS84坐标系计算结果："+meter2 + "米");
    }

}

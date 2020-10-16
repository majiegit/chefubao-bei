package com.sw.chefubao.controller;

import com.sw.chefubao.common.R;
import com.sw.chefubao.common.util.GlobaCoordinatesUtil;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;
import org.junit.Test;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;


@RestController
public class TestController {


    @Test
    public void test() {

        GlobalCoordinates source = new GlobalCoordinates(40.659151,109.808468);
        GlobalCoordinates target = new GlobalCoordinates(40.660608,109.815441);
        BigDecimal meter1 = GlobaCoordinatesUtil.getDistanceMeter(source, target, Ellipsoid.Sphere);
        BigDecimal meter2 = GlobaCoordinatesUtil.getDistanceMeter(source, target, Ellipsoid.WGS84);

        System.out.println("Sphere坐标系计算结果：" + meter1 + "米");
        System.out.println("WGS84坐标系计算结果：" + meter2 + "米");
    }

}

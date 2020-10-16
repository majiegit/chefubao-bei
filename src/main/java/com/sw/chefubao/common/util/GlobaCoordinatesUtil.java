package com.sw.chefubao.common.util;

import cn.hutool.core.util.NumberUtil;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;

import java.math.BigDecimal;

/**
 * 地图工具类
 * @author MJ
 */
public class GlobaCoordinatesUtil {
    /**
     * 根据经纬度计算距离
     * @param gpsFrom
     * @param gpsTo
     * @param ellipsoid
     * @return
     */
    public  static BigDecimal getDistanceMeter(GlobalCoordinates gpsFrom, GlobalCoordinates gpsTo, Ellipsoid ellipsoid){

        //创建GeodeticCalculator，调用计算方法，传入坐标系、经纬度用于计算距离
        GeodeticCurve geoCurve = new GeodeticCalculator().calculateGeodeticCurve(ellipsoid, gpsFrom, gpsTo);
        geoCurve.getEllipsoidalDistance();
        BigDecimal round = NumberUtil.round(geoCurve.getEllipsoidalDistance(), 0);
        return round;
    }
}

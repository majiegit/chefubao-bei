package com.sw.chefubao.controller;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.sw.chefubao.common.R;
import com.sw.chefubao.entity.CarType;
import com.sw.chefubao.service.CarTypeService;
import com.sw.chefubao.service.SysRegionService;
import com.sw.chefubao.service.UserLicenseTagApplyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.element.NestingKind;
import java.util.*;

/**
 * 统计
 */
@RestController
@RequestMapping("/chart")
public class StatisticsController {
    @Autowired
    private UserLicenseTagApplyInfoService userLicenseTagApplyInfoService;
    @Autowired
    private CarTypeService carTypeService;
    @Autowired
    private SysRegionService sysRegionService;

    /**
     * 上半年增长量
     *
     * @return
     */
    @GetMapping("/chartByOn")
    public R chartByOn(Integer regionId) {
        StringBuffer stringBuffer1 = new StringBuffer();
        String regionName = sysRegionService.getRegionName(stringBuffer1, regionId);
        String address = ".*";
        String substring = "";
        if (regionName.length() != 0) {
            substring = regionName.substring(0, regionName.length() - 1);
        }
        if (ObjectUtil.isNotEmpty(substring)) {
            address = "^" + substring + ".*";
        }

        LinkedHashMap<String, List> resultMap = new LinkedHashMap<>();
        List categorieList = new LinkedList<>();
        List seriesList = new LinkedList<>();
        List seriesDataList = new LinkedList<>();
        DateTime dateTime = DateUtil.beginOfYear(new Date());
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        for (int i = 0; i < 6; i++) {
            DateTime startTime = DateUtil.offsetMonth(dateTime, i);
            DateTime endTime = DateUtil.endOfMonth(startTime);
            categorieList.add(i + 1 + "月");
            int statics = userLicenseTagApplyInfoService.statics(startTime, endTime, address);
            seriesDataList.add(statics);
        }
        map.put("name", "上半年增量");
        map.put("data", seriesDataList);
        seriesList.add(map);
        resultMap.put("categories", categorieList);
        resultMap.put("series", seriesList);
        return R.SELECT_SUCCESS.data(resultMap);
    }

    /**
     * 下半年增长量
     *
     * @return
     */
    @GetMapping("/chartByDown")
    public R chartByDown(Integer regionId) {
        StringBuffer stringBuffer1 = new StringBuffer();
        String regionName = sysRegionService.getRegionName(stringBuffer1, regionId);
        String address = ".*";
        String substring = "";
        if (regionName.length() != 0) {
            substring = regionName.substring(0, regionName.length() - 1);
        }
        if (ObjectUtil.isNotEmpty(substring)) {
            address = "^" + substring + ".*";
        }
        LinkedHashMap<String, List> resultMap = new LinkedHashMap<>();
        List categorieList = new LinkedList<>();
        List seriesList = new LinkedList<>();
        List seriesDataList = new LinkedList<>();
        DateTime dateTime = DateUtil.beginOfYear(new Date());
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        for (int i = 6; i < 12; i++) {
            DateTime startTime = DateUtil.offsetMonth(dateTime, i);
            DateTime endTime = DateUtil.endOfMonth(startTime);
            categorieList.add(i + 1 + "月");
            int statics = userLicenseTagApplyInfoService.statics(startTime, endTime, address);
            seriesDataList.add(statics);
        }
        map.put("name", "上半年增量");
        map.put("data", seriesDataList);
        seriesList.add(map);
        resultMap.put("categories", categorieList);
        resultMap.put("series", seriesList);
        return R.SELECT_SUCCESS.data(resultMap);
    }

    /**
     * 区域总上牌量
     *
     * @param regionId
     * @return
     */
    @GetMapping("/total")
    public R getTotal(Integer regionId) {
        StringBuffer stringBuffer1 = new StringBuffer();
        String regionName = sysRegionService.getRegionName(stringBuffer1, regionId);
        String address = ".*";
        String substring = "";
        if (regionName.length() != 0) {
            substring = regionName.substring(0, regionName.length() - 1);
        }
        if (ObjectUtil.isNotEmpty(substring)) {
            address = "^" + substring + ".*";
        }
        LinkedList<Map> maps = new LinkedList<>();
        List<CarType> list = carTypeService.list();
        Integer chineseTotal = 0;
        Integer notTotal = 0;
        Integer lineTotal = 0;
        for (CarType carType : list) {
            String carTypeName = carType.getCarTypeName();
            int i = userLicenseTagApplyInfoService.staticsTotal(address, carType.getId());
            LinkedHashMap<String, Object> map = new LinkedHashMap<>();
            map.put("carTypeName", carTypeName);
            if (carTypeName.matches("国标")) {
                map.put("chineseStandard", i);
                map.put("notStandard", 0);
                chineseTotal += i;
            } else {
                map.put("chineseStandard", 0);
                map.put("notStandard", i);
                notTotal += i;

            }
            lineTotal += i;
            map.put("lineTotal", i);
            maps.add(map);
        }
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("carTypeName", "合计");
        map.put("chineseStandard", chineseTotal);
        map.put("notStandard", notTotal);
        map.put("lineTotal", lineTotal);
        maps.add(map);
        return R.SELECT_SUCCESS.data(maps);
    }

    /**
     * 日 周 月  旬 区域总上牌量
     *
     * @param regionId
     * @return
     */
    @GetMapping("/getTotalByCondition")
    public R getTotalByCondition(Integer regionId, String name) {

        HashMap<String, DateTime> date = getDate(name);
        DateTime startTime = date.get("startTime");
        DateTime endTime = date.get("endTime");
        StringBuffer stringBuffer1 = new StringBuffer();
        String regionName = sysRegionService.getRegionName(stringBuffer1, regionId);
        String address = ".*";
        String substring = "";
        if (regionName.length() != 0) {
            substring = regionName.substring(0, regionName.length() - 1);
        }
        if (ObjectUtil.isNotEmpty(substring)) {
            address = "^" + substring + ".*";
        }
        LinkedList<Map> maps = new LinkedList<>();
        List<CarType> list = carTypeService.list();
        Integer chineseTotal = 0;
        Integer notTotal = 0;
        Integer lineTotal = 0;
        for (CarType carType : list) {
            String carTypeName = carType.getCarTypeName();
            int i = userLicenseTagApplyInfoService.staticsTotalByTime(address, carType.getId(), startTime, endTime);
            LinkedHashMap<String, Object> map = new LinkedHashMap<>();
            map.put("carTypeName", carTypeName);
            if (carTypeName.matches("国标")) {
                map.put("chineseStandard", i);
                map.put("notStandard", 0);
                chineseTotal += i;
            } else {
                map.put("chineseStandard", 0);
                map.put("notStandard", i);
                notTotal += i;

            }
            map.put("lineTotal", i);
            maps.add(map);
            lineTotal += i;
        }
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("carTypeName", "合计");
        map.put("chineseStandard", chineseTotal);
        map.put("notStandard", notTotal);
        map.put("lineTotal", lineTotal);
        maps.add(map);
        return R.SELECT_SUCCESS.data(maps);
    }

    /**
     * 根据时间 区域总上牌量
     *
     * @param regionId
     * @return
     */
    @GetMapping("/getTotalByTime")
    public R getTotalByTime(Integer regionId, String startDate, String endDate) {

        DateTime startTime = DateUtil.parseDate(startDate);
        DateTime endTime = DateUtil.parseDate(endDate);
        StringBuffer stringBuffer1 = new StringBuffer();
        String regionName = sysRegionService.getRegionName(stringBuffer1, regionId);
        String address = ".*";
        String substring = "";
        if (regionName.length() != 0) {
            substring = regionName.substring(0, regionName.length() - 1);
        }
        if (ObjectUtil.isNotEmpty(substring)) {
            address = "^" + substring + ".*";
        }
        LinkedList<Map> maps = new LinkedList<>();
        List<CarType> list = carTypeService.list();
        Integer chineseTotal = 0;
        Integer notTotal = 0;
        Integer lineTotal = 0;
        for (CarType carType : list) {
            String carTypeName = carType.getCarTypeName();
            int i = userLicenseTagApplyInfoService.staticsTotalByTime(address, carType.getId(), startTime, endTime);
            LinkedHashMap<String, Object> map = new LinkedHashMap<>();
            map.put("carTypeName", carTypeName);
            if (carTypeName.matches("国标")) {
                map.put("chineseStandard", i);
                map.put("notStandard", 0);
                chineseTotal += i;
            } else {
                map.put("chineseStandard", 0);
                map.put("notStandard", i);
                notTotal += i;

            }
            map.put("lineTotal", i);
            lineTotal += i;
            maps.add(map);
        }
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("carTypeName", "合计");
        map.put("chineseStandard", chineseTotal);
        map.put("notStandard", notTotal);
        map.put("lineTotal", lineTotal);
        maps.add(map);
        return R.SELECT_SUCCESS.data(maps);
    }

    private HashMap<String, DateTime> getDate(String name) {
        HashMap<String, DateTime> map = new HashMap<>();
        Date date = new Date();
        if ("day".equals(name)) {
            map.put("startTime", DateUtil.beginOfDay(date));
            map.put("endTime", DateUtil.endOfDay(date));
            return map;
        }
        if ("week".equals(name)) {
            map.put("startTime", DateUtil.beginOfWeek(date));
            map.put("endTime", DateUtil.endOfWeek(date));
            return map;
        }
        if ("period".equals(name)) {
            DateTime startTime = DateUtil.beginOfMonth(date);
            DateTime tenTime = DateUtil.offsetDay(startTime, 10);
            DateTime twoTenTime = DateUtil.offsetDay(tenTime, 10);
            DateTime endTime = DateUtil.endOfMonth(date);
            long time = date.getTime();
            if (time >= startTime.getTime() && time < tenTime.getTime()) {
                map.put("startTime", startTime);
                map.put("endTime", tenTime);
                return map;
            }
            if (time >= tenTime.getTime() && time < twoTenTime.getTime()) {
                map.put("startTime", tenTime);
                map.put("endTime", twoTenTime);
                return map;
            }
            if (time >= twoTenTime.getTime()) {
                map.put("startTime", twoTenTime);
                map.put("endTime", endTime);
                return map;
            }
            return map;
        }
        if ("month".equals(name)) {
            map.put("startTime", DateUtil.beginOfMonth(date));
            map.put("endTime", DateUtil.endOfMonth(date));
            return map;
        }
        return map;
    }
}

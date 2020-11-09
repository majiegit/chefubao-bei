package com.sw.chefubao.controller;


import com.sw.chefubao.common.R;
import com.sw.chefubao.entity.SysRegion;
import com.sw.chefubao.service.SysRegionService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/sysRegion")
public class SysRegionController {
    @Autowired
    private SysRegionService sysRegionService;

    private static Map<Integer, String> cssMap = new HashMap<>();

    static {
        cssMap.put(1, "provincetr");// 省
        cssMap.put(2, "citytr");// 市
        cssMap.put(3, "countytr");// 县
        cssMap.put(4, "towntr");// 镇
        cssMap.put(5, "villagetr");// 村
    }

    @GetMapping("/test")
    public R main() throws IOException {
        int level = 1;

        // 获取全国各个省级信息
        Document connect = connect("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/");
        Elements rowProvince = connect.select("tr." + cssMap.get(level));
        for (Element provinceElement : rowProvince)// 遍历每一行的省份城市
        {
            Elements select = provinceElement.select("a");
            for (Element province : select)// 每一个省份(四川省)
            {
                SysRegion sysRegion = new SysRegion();
                sysRegion.setPid(0);
                sysRegion.setName(province.text());
                sysRegion.setCreateTime(new Date());
                sysRegionService.save(sysRegion);
                parseNextLevel(sysRegion.getId(), province, level + 1);
            }
        }
        return null;
    }


    private void parseNextLevel(Integer id, Element parentElement, int level) throws IOException {
        try {
            Thread.sleep(50);//睡眠一下，否则可能出现各种错误状态码
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Document doc = connect(parentElement.attr("abs:href"));
        if (doc != null) {
            Elements newsHeadlines = doc.select("tr." + cssMap.get(level));//
            // 获取表格的一行数据
            for (Element element : newsHeadlines) {
                String text1 = element.text();
                if (!"县".equals(text1)) {
                    Elements select = element.select("a");// 在递归调用的时候，这里是判断是否是村一级的数据，村一级的数据没有a标签
                    if (select.size() != 0) {
                        SysRegion sysRegion = new SysRegion();
                        sysRegion.setPid(id);
                        String text = select.last().text();
                        if (!text.equals("县")) {
                            int i = text.indexOf("办事处");
                            if (i != -1) {
                                sysRegion.setName(text.substring(0, i));
                            } else {
                                sysRegion.setName(text);
                            }
                            sysRegion.setCreateTime(new Date());
                            sysRegionService.save(sysRegion);
                            parseNextLevel(sysRegion.getId(), select.last(), level + 1);
                        }else{
                            System.out.println("县 不保存");
                        }
                    }
                }
            }
        }
    }

    private static Document connect(String url) {
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("The input url('" + url + "') is invalid!");
        }
        try {
            return Jsoup.connect(url).timeout(10 * 1000).get();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 查询权限名称树
     */
    @GetMapping("/tree")
    public R list(Integer permissionValue) {
        List<Object> tree = sysRegionService.tree(permissionValue);
        return R.SELECT_SUCCESS.data(tree);
    }

    /**
     * 查询可以上的权限集合
     */
    @GetMapping("/getlist")
    public R getList(Integer regionId) {
        List<SysRegion> list = sysRegionService.getList(regionId);
        return R.SELECT_SUCCESS.data(list);
    }
}

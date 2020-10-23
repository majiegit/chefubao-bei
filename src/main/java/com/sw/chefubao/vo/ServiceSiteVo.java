package com.sw.chefubao.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 服务点Vo
 */
@Getter
@Setter
@ToString
public class ServiceSiteVo implements Comparable<ServiceSiteVo> {
    private Integer id;
    private String name;
    private String address;
    private String photo;
    private String longitude;
    private String latitude;
    private Integer distance;

    @Override
    public int compareTo(ServiceSiteVo serviceSiteVo) {
        return this.distance - serviceSiteVo.getDistance();
    }
}

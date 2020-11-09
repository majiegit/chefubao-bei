package com.sw.chefubao.common.enums;

/**
 * 后台用户类型
 *
 * @author MJ
 */
public enum SysUserTypeEnum {
    USER_ADMIN(0, "管理员"),
    USER_MAIL(1, "邮寄账号"),
    USER_SERVICE(2, "服务点账号"),
    USER_SHENG(3, "省级账号"),
    USER_SHI(4, "市级账号"),
    USER_QU(5, "区级账号"),
    USER_JIE(6, "街道级账号");
    private Integer key;
    private String desc;

    SysUserTypeEnum(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public Integer getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }

    public static SysUserTypeEnum getAppStatus(Integer key) {
        for (SysUserTypeEnum status : SysUserTypeEnum.values()) {
            if (status.key.equals(key)) {
                return status;
            }
        }
        return null;
    }
}

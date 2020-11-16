package com.sw.chefubao.common.config;

public class WechatConfig {
    //小程序appid
    public static final String appid = "wx462bcf5dc430713f";
    //微信支付的商户id
    public static final String mch_id = "1602693490";
    //微信支付的商户密钥
    public static final String key = "9318ba26fb0fd44e0c16be3a87d1067c";
    // 微信secret
    public static final String secret = "2167d0953e9d114ce8a108bba3118047";
    //支付成功后的服务器回调url，这里填PayController里的回调函数地址
    public static final String notify_url = "http://39.99.138.203:8080/chefubao/orderpay/wxNotify";
    public static final String price_url = "http://39.99.138.203:8080/chefubao/pay/wxNotify";
    //交易类型，小程序支付的固定值为JSAPI
    public static final String TRADETYPE = "JSAPI";
    //微信统一下单接口地址
    public static final String pay_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    //微信用户唯一标识地址
    public static final String openid_url = "https://api.weixin.qq.com/sns/jscode2session";
}

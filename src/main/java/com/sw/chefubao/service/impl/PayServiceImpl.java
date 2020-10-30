package com.sw.chefubao.service.impl;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.github.wxpay.sdk.WXPayUtil;
import com.sw.chefubao.common.config.WechatConfig;
import com.sw.chefubao.common.enums.OrderStatusEnum;
import com.sw.chefubao.common.util.HttpRequest;
import com.sw.chefubao.common.util.PayUtil;
import com.sw.chefubao.entity.OrderTable;
import com.sw.chefubao.mapper.OrderTableMapper;
import com.sw.chefubao.service.PayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private OrderTableMapper orderTableMapper;

    Logger logger = LoggerFactory.getLogger(PayServiceImpl.class);

    /**
     * 支付成功修改订单状态为待发货
     *
     * @param orderId
     * @return
     */
    public int updateOrderStatus(String orderId) {
        int id = NumberUtil.parseInt(orderId);
        int update = orderTableMapper.updateOrderStatus(id, OrderStatusEnum.ORDER_WAIT_FOR_SHIPMENTS.getKey());
        return update;
    }

    /**
     * 微信预支付下单
     *
     * @param spbill_create_ip
     * @param code
     * @param orderId
     * @return
     */
    public Map wxPay(String spbill_create_ip, String code, Integer orderId) {
        // 根据订单ID 查询 订单商品总价
        OrderTable order = orderTableMapper.selectById(orderId);
        Integer totalPrice = order.getPayTotal();
        //页面获取openId接口
        String getopenid_url = "https://api.weixin.qq.com/sns/jscode2session";
        String param = "appid=" + WechatConfig.appid + "&secret=" + WechatConfig.secret + "&js_code=" + code + "&grant_type=authorization_code";
        //向微信服务器发送get请求获取openIdStr
        String openIdStr = HttpRequest.sendGet(getopenid_url, param);
        JSONObject json = JSONUtil.parseObj(openIdStr);//转成Json格式
        String openId = json.get("openid").toString();//获取openId

        Map<String, Object> payMap = new HashMap<String, Object>();//返回给小程序端需要的参数
        try {
            //生成的随机字符串
            String nonce_str = WXPayUtil.generateNonceStr();
            //商品名称
            String body = "测试商品名称";

            //组装参数，用户生成统一下单接口的签名
            logger.info("----------下单接口签名-------");
            Map<String, String> packageParams = new HashMap<>();
            packageParams.put("appid", WechatConfig.appid);
            packageParams.put("mch_id", WechatConfig.mch_id);
            packageParams.put("nonce_str", nonce_str);
            packageParams.put("body", body);
            packageParams.put("out_trade_no", orderId + "");//商户订单号,自己的订单ID
            packageParams.put("total_fee", totalPrice + "");//支付金额，这边需要转成字符串类型，否则后面的签名会失败
            packageParams.put("spbill_create_ip", spbill_create_ip);
            packageParams.put("notify_url", WechatConfig.notify_url);//支付成功后的回调地址
            packageParams.put("trade_type", WechatConfig.TRADETYPE);//支付方式
            packageParams.put("openid", openId + "");//用户的openID，自己获取
            String sign = WXPayUtil.generateSignature(packageParams, WechatConfig.key);
            logger.info("----------mysign:" + sign);
            packageParams.put("sign", sign);
            String xml = WXPayUtil.mapToXml(packageParams);
            // String prestr = PayUtil.createLinkString(packageParams); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
            //  logger.info("----------prestr:" + prestr);
            //MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
            //  String mysign = PayUtil.sign(prestr, WechatConfig.key, "utf-8").toUpperCase();
            //拼接统一下单接口使用的xml数据，要将上一步生成的签名一起拼接进去

            //调用统一下单接口，并接受返回的结果
            String result = HttpRequest.sendPost(WechatConfig.pay_url, xml);

            logger.info("----------result:" + result);

            // 将解析结果存储在HashMap中
            Map map = PayUtil.doXMLParse(result);
            String return_code = (String) map.get("return_code");//返回状态码
            String result_code = (String) map.get("result_code");//返回状态码

            if (return_code.equals("SUCCESS") || return_code.equals(result_code)) {
                String prepay_id = (String) map.get("prepay_id");//返回的预付单信息
                payMap.put("nonceStr", nonce_str);
                payMap.put("package", "prepay_id=" + prepay_id);
                Long timeStamp = System.currentTimeMillis() / 1000;
                payMap.put("timeStamp", timeStamp + "");//这边要将返回的时间戳转化成字符串，不然小序程端调用wx.requestPayment方法会报签名错误
                //拼接签名需要的参数
                String stringSignTemp = "appId=" + WechatConfig.appid + "&nonceStr=" + nonce_str + "&package=prepay_id=" + prepay_id + "&signType=MD5&timeStamp=" + timeStamp;
                //再次签名，这个签名用于小程序端调用wx.requesetPayment方法
                String paySign = PayUtil.sign(stringSignTemp, WechatConfig.key, "utf-8").toUpperCase();
                logger.info("=======================第二次签名：", paySign + "============ ======");
                payMap.put("paySign", paySign);
            } else {
                logger.info("----------统一下单失败-------");
                return payMap;
            }
            payMap.put("appid", WechatConfig.appid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payMap;
    }
}

package com.saodiseng.eduorder.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.wxpay.sdk.WXPayUtil;
import com.saodiseng.eduorder.entity.TOrder;
import com.saodiseng.eduorder.entity.TPayLog;
import com.saodiseng.eduorder.mapper.TPayLogMapper;
import com.saodiseng.eduorder.service.TOrderService;
import com.saodiseng.eduorder.service.TPayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.saodiseng.eduorder.utils.HttpClient;
import com.saodiseng.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-06-23
 */
@Service
public class TPayLogServiceImpl extends ServiceImpl<TPayLogMapper, TPayLog> implements TPayLogService {
    @Autowired
    private TOrderService orderService;

    //生成微信支付二维码接口
    @Override
    public Map<String,Object> createNativeQRCode(String orderNo) {
        try {
            //1根据订单号查订单信息
            QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
            wrapper.eq("order_no",orderNo);
            TOrder order = orderService.getOne(wrapper);
            //2map集合设置生成二维码需要的东西
            Map<String,String> m = new HashMap<>();
            m.put("appid","wx74862e0dfcf69954");
            m.put("mch_id", "1558950191");
            m.put("nonce_str", WXPayUtil.generateNonceStr()); //随机字符串，生成2微码
            m.put("body", order.getCourseTitle());
            m.put("out_trade_no", orderNo);
            m.put("total_fee", order.getTotalFee().multiply(new BigDecimal("100")).longValue()+"");
            m.put("spbill_create_ip", "127.0.0.1");
            m.put("notify_url", "http://guli.shop/api/order/weixinPay/weixinNotify\n");
            m.put("trade_type", "NATIVE");
            //3发送HttpClient传递参数，xml格式
            HttpClient httpClient = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");
            //设置xml格式参数
            httpClient.setXmlParam(WXPayUtil.generateSignedXml(m,"T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
            httpClient.setHttps(true);
            //执行post请求发送
            httpClient.post();
            //4得到返回结果
            //返回内容是xml格式
            String xml = httpClient.getContent();
            //xml转map
            Map<String,String> resultMap = WXPayUtil.xmlToMap(xml); //包含很多信息,重新封装一个map
            Map<String,Object> map = new HashMap<>();
            map.put("out_trade_no", orderNo);
            map.put("course_id", order.getCourseId());
            map.put("total_fee", order.getTotalFee());
            map.put("result_code", resultMap.get("result_code"));//二维码状态码
            map.put("code_url", resultMap.get("code_url")); //二维码地址
            return map;
        } catch (Exception e){
            throw new GuliException(20001,"生成二维码失败");
        }
    }
}

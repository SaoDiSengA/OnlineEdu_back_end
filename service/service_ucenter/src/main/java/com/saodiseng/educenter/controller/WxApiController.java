package com.saodiseng.educenter.controller;

import com.saodiseng.educenter.utils.ConstantWxUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLEncoder;

@Controller    //不需要返回数据因此不用restcontroller
@RequestMapping("/api/ucenter/wx")
@CrossOrigin
public class WxApiController{
    //扫描二维码，获取人员信息
    @GetMapping("callback")
    public String callback(String code,String state){
        System.out.println(code+state);
        return "redirect:http://localhost:3000";
    }
    //生成微信扫描二维码
    @GetMapping("login")
    public String getWxCode(){
        String url = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";
        //对redirect_url编码
        String redirect_url = ConstantWxUtils.REDIRECT_URL;
        try{
            redirect_url = URLEncoder.encode(redirect_url, "utf-8");
        }catch (Exception e){

        }
        String qrcodeUrl = String.format(
                url,
                ConstantWxUtils.APP_ID,
                redirect_url,
                "atguigu"
        );
        //重定向请求微信地址
        return "redirect:" + qrcodeUrl;
    }
}

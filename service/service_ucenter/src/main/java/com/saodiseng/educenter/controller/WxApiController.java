package com.saodiseng.educenter.controller;

import com.google.gson.Gson;
import com.saodiseng.commonutils.JwtUtils;
import com.saodiseng.educenter.entity.UcenterMember;
import com.saodiseng.educenter.service.UcenterMemberService;
import com.saodiseng.educenter.utils.ConstantWxUtils;
import com.saodiseng.educenter.utils.HttpClientUtils;
import com.saodiseng.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLEncoder;
import java.util.HashMap;

@Controller    //不需要返回数据因此不用restcontroller
@RequestMapping("/api/ucenter/wx")
@CrossOrigin
public class WxApiController{
    @Autowired
    private UcenterMemberService memberService;
    //扫描二维码，获取人员信息
    @GetMapping("callback")
    public String callback(String code,String state){
        try{
            //获取code值，类似于验证码

            //拿着code请求微信固定地址，获取到access_token,openid
            String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                    "?appid=%s" +
                    "&secret=%s" +
                    "&code=%s" +
                    "&grant_type=authorization_code";
            //拼接三个参数id 密钥 code
            String accessTokenUrl = String.format(
                    baseAccessTokenUrl,
                    ConstantWxUtils.APP_ID,
                    ConstantWxUtils.APP_SECRET,
                    code
            );
            //请求拼接的地址，得到返回值access_token,openid
            //httpclient发送请求，得到结果
            String accessTokenInfo = HttpClientUtils.get(accessTokenUrl);
            System.out.println("accessTokenInfo:"+accessTokenInfo);
            //从accessTokenInfo中拿出access_token,openid
            //accessTokenInfo转换成map集合，通过key拿到对应值
            //使用json转换工具------gson
            Gson gson = new Gson();
            HashMap mapAccessToken = gson.fromJson(accessTokenInfo, HashMap.class);
            String access_token = (String) mapAccessToken.get("access_token");
            String openid = (String) mapAccessToken.get("openid");
            //拿着这两个值，请求微信固定地址，获取扫码信息
            String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                    "?access_token=%s" +
                    "&openid=%s";
            String userInfoUrl = String.format(baseUserInfoUrl, access_token, openid);
            String userInfo = HttpClientUtils.get(userInfoUrl);
            System.out.println("userInfo:"+userInfo);

            HashMap mapUserInfo = gson.fromJson(userInfo, HashMap.class);
            String nickname = (String) mapUserInfo.get("nickname");
            String headimgurl = (String) mapUserInfo.get("headimgurl");

            //扫码人信息添加到数据库
            //判断数据库是否有相同得微信信息，相同的不加，openid
            UcenterMember member = memberService.getOpenIdMember(openid);
            if (member == null){
                //member是空，则加
                member = new UcenterMember();
                member.setOpenid(openid);
                member.setNickname(nickname);
                member.setAvatar(headimgurl);
                memberService.save(member);
            }
            //使用jwt根据member对象生成token
            String jwtToken = JwtUtils.getJwtToken(member.getId(), member.getNickname());
            //最后返回首页面，通过路径传递token
            return "redirect:http://localhost:3000?token="+jwtToken;
        }catch (Exception e){
            throw new GuliException(20001,"登陆失败");
        }


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

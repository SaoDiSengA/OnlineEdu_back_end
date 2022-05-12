package com.saodiseng.educenter.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstantWxUtils implements InitializingBean {
    //读取配置文件内容
    @Value("${wx.open.app_id}")
    private String app_id;

    @Value("${wx.open.app_secret}")
    private String app_secret;

    @Value("${wx.open.redirect_url}")
    private String redirect_url;

    //定义公开静态常量
    public static String APP_ID;
    public static String APP_SECRET;
    public static String REDIRECT_URL;

    @Override
    public void afterPropertiesSet() throws Exception {
        APP_ID = app_id;
        APP_SECRET = app_secret;
        REDIRECT_URL = redirect_url;
    }
}

package com.saodiseng.eduorder.service;

import com.saodiseng.eduorder.entity.TPayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-06-23
 */
public interface TPayLogService extends IService<TPayLog> {

    //生成微信支付二维码
    Map<String,Object> createNativeQRCode(String orderNo);

    //根据订单号查询支付状态
    Map<String, String> queryPayStatus(String orderNo);

    //向支付表中添加记录，更新订单状态
    void updateOrderStatus(Map<String, String> map);
}

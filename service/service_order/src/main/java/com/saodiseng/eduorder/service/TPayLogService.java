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

    Map<String,Object> createNativeQRCode(String orderNo);
}

package com.saodiseng.eduorder.service;

import com.saodiseng.eduorder.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-06-23
 */
public interface TOrderService extends IService<TOrder> {

    String createOrder(String courseId, String memberIdByJwtToken);
}

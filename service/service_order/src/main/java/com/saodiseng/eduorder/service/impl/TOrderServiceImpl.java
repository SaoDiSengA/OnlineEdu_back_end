package com.saodiseng.eduorder.service.impl;

import com.saodiseng.eduorder.entity.TOrder;
import com.saodiseng.eduorder.mapper.TOrderMapper;
import com.saodiseng.eduorder.service.TOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-06-23
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {

    //生成订单接口
    @Override
    public String createOrder(String courseId, String memberIdByJwtToken) {
        //通过远程调用获取用户信息，用户id

        //通过远程调用获取课程信息，课程id
        return null;
    }
}

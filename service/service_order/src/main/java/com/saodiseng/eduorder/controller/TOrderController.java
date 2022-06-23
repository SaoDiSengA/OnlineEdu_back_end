package com.saodiseng.eduorder.controller;


import com.saodiseng.commonutils.JwtUtils;
import com.saodiseng.commonutils.R;
import com.saodiseng.eduorder.service.TOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-06-23
 */
@RestController
@RequestMapping("/eduorder/order")
@CrossOrigin
public class TOrderController {
    @Autowired
    private TOrderService orderService;
    //生成订单接口
    @PostMapping("createOrder/{courseId}")
    public R createOrder(@PathVariable String courseId, HttpServletRequest request){
        //创建订单，返回订单号
        String orderNo = orderService.createOrder(courseId, JwtUtils.getMemberIdByJwtToken(request));
        return R.ok().data("orderId",orderNo);
    }
    //根据订单id查询订单信息

    //生成微信二维码

    //查询订单支付状态接口
}
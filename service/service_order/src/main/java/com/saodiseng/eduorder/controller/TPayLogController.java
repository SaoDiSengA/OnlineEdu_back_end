package com.saodiseng.eduorder.controller;


import com.saodiseng.commonutils.R;
import com.saodiseng.eduorder.service.TPayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.service.ApiListing;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-06-23
 */
@RestController
@RequestMapping("/eduorder/paylog")
@CrossOrigin
public class TPayLogController {
    @Autowired
    private TPayLogService payLogService;
    //生成微信支付二维码接口
    //参数是订单号
    @GetMapping("createNativeQRCode/{orderNo}")
    public R createNativeQRCode(@PathVariable String orderNo){
        //返回信息，包含二维码地址，还有其他信息
        Map<String, Object> map = payLogService.createNativeQRCode(orderNo);
        System.out.println("============返回二维码的map集合：" + map);
        return R.ok().data(map);
    }

    //查询订单支付状态接口
    //参数是订单号，根据订单号查询支付状态
    @GetMapping("queryPayStatus/{orderNo}")
    public R queryPayStatus(@PathVariable String orderNo){
        Map<String, String> map = payLogService.queryPayStatus(orderNo);
        System.out.println("============查询订单状态的map集合：" + map);
        if (map == null){
            return R.error().message("支付出错");
        }
        //如果map不为空，通过map，获取订单状态
        if (map.get("trade_state").equals("SUCCESS")){
            //添加记录到支付表里，更新订单表订单状态
            payLogService.updateOrderStatus(map);
            return R.ok().message("支付成功");
        }
        return R.ok().code(25000).message("支付中");
    }
}


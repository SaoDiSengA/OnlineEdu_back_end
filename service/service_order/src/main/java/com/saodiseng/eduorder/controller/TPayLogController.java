package com.saodiseng.eduorder.controller;


import com.saodiseng.commonutils.R;
import com.saodiseng.eduorder.service.TPayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return R.ok().data(map);
    }
}


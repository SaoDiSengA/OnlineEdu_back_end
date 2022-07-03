package com.saodiseng.educms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.saodiseng.commonutils.R;
import com.saodiseng.educms.entity.CrmBanner;
import com.saodiseng.educms.service.CrmBannerService;
import org.bouncycastle.cert.crmf.CertificateRequestMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前台banner显示
 * </p>
 *
 * @author testjava
 * @since 2022-04-30
 */
@RestController
@RequestMapping("/educms/bannerfront")
public class BannerFrontController {
    @Autowired
    private CrmBannerService bannerService;

    // 查询所有banner
    @GetMapping("getAllBanner")
    public R getAllBanner(){
        List<CrmBanner> list = bannerService.selectAllBanner();    //单独写service是为了redis方便，也可以直接用mybatis的方法
        return R.ok().data("list",list);
    }
}

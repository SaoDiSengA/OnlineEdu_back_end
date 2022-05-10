package com.saodiseng.educenter.controller;


import com.saodiseng.commonutils.R;
import com.saodiseng.educenter.entity.UcenterMember;
import com.saodiseng.educenter.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author saodiseng
 * @since 2022-05-10
 */
@RestController
@RequestMapping("/eduucenter/member")
@CrossOrigin
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService memberService;

    //登录
    @PostMapping("login")
    public R loginUser(@RequestBody UcenterMember member){
        //调用service方法实现登录
        //返回token值，使用jwt生成
        String token = memberService.login(member);
        return R.ok().data("token",token);
    }
    //注册
}


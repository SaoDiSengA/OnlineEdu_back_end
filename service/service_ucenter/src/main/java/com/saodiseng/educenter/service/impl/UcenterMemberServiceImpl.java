package com.saodiseng.educenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.saodiseng.commonutils.JwtUtils;
import com.saodiseng.commonutils.MD5;
import com.saodiseng.educenter.entity.UcenterMember;
import com.saodiseng.educenter.mapper.UcenterMemberMapper;
import com.saodiseng.educenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.saodiseng.servicebase.exceptionhandler.GuliException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author saodiseng
 * @since 2022-05-10
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    //登录方法
    @Override
    public String login(UcenterMember member) {
        //获取登录的手机号和密码
        String mobile = member.getMobile();
        String password = member.getPassword();
        //手机号密码非空判断
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
            throw new GuliException(20001,"登陆失败");
        }
        //判断手机号是否正确
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        UcenterMember mobileMember = baseMapper.selectOne(wrapper);
        //判断查询对象是否为空
        if (mobileMember == null){ //没有这个手机号
            throw new GuliException(20001,"登陆失败");
        }

        //判断密码
        //因为存储到数据库的密码是加密的
        //把输入的密码进行加密，在与数据库进行比较
        //MD5
        if (!MD5.encrypt(password).equals(mobileMember.getPassword())) {
            throw new GuliException(20001,"登陆失败");
        }
        //判断用户是否禁用，表中is_disable
        if (mobileMember.getIsDisabled()){
            throw new GuliException(20001,"登陆失败");
        }
        //登陆成功
        //生成token字符串，使用jwt工具类
        String jwtToken = JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());
        return jwtToken;
    }
}

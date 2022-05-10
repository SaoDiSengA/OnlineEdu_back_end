package com.saodiseng.educenter.service;

import com.saodiseng.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.saodiseng.educenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author saodiseng
 * @since 2022-05-10
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(UcenterMember member);

    void register(RegisterVo registerVo);
}

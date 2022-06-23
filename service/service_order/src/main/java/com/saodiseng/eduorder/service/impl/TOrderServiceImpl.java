package com.saodiseng.eduorder.service.impl;

import com.saodiseng.commonutils.ordervo.CourseWebVoOrder;
import com.saodiseng.commonutils.ordervo.UcenterMemberOrder;
import com.saodiseng.eduorder.client.EduClient;
import com.saodiseng.eduorder.client.UcenterClient;
import com.saodiseng.eduorder.entity.TOrder;
import com.saodiseng.eduorder.mapper.TOrderMapper;
import com.saodiseng.eduorder.service.TOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.saodiseng.eduorder.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private EduClient eduClient;

    @Autowired
    private UcenterClient ucenterClient;

    //生成订单接口
    @Override
    public String createOrder(String courseId, String memberIdByJwtToken) {
        //通过远程调用获取用户信息，用户id
        UcenterMemberOrder userInfoOrder = ucenterClient.getUserInfoOrder(memberIdByJwtToken);
        //通过远程调用获取课程信息，课程id
        CourseWebVoOrder courseInfoOrder = eduClient.getCourseInfoOrder(courseId);
        //创建order对象，设置数据，添加，返回订单号
        TOrder tOrder = new TOrder();
        tOrder.setOrderNo(OrderNoUtil.getOrderNo());//定单号
        tOrder.setCourseId(courseId);
        tOrder.setCourseTitle(courseInfoOrder.getTitle());
        tOrder.setCourseCover(courseInfoOrder.getCover());
        tOrder.setTeacherName(courseInfoOrder.getTeacherName());
        tOrder.setTotalFee(courseInfoOrder.getPrice());
        tOrder.setMemberId(memberIdByJwtToken);
        tOrder.setMobile(userInfoOrder.getMobile());
        tOrder.setNickname(userInfoOrder.getNickname());
        tOrder.setStatus(0);  //支付状态
        tOrder.setPayType(1);   //支付类型，微信（支付宝）
        baseMapper.insert(tOrder);
        return tOrder.getOrderNo();
    }
}

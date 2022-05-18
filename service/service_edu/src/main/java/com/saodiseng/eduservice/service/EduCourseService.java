package com.saodiseng.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.saodiseng.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.saodiseng.eduservice.entity.frontVo.CourseFrontVo;
import com.saodiseng.eduservice.entity.frontVo.CourseWebVo;
import com.saodiseng.eduservice.entity.vo.CourseInfoVo;
import com.saodiseng.eduservice.entity.vo.CoursePublishVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-03-14
 */
public interface EduCourseService extends IService<EduCourse> {

    //添加课程基本信息的方法
    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String id);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo publishCourseInfo(String id);

    void removeCourse(String courseId);

    Map<String, Object> getCourseFrontList(Page<EduCourse> coursePage, CourseFrontVo courseFrontVo);

    CourseWebVo getBaseCourseInfo(String courseId);
}

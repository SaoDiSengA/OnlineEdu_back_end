package com.saodiseng.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.saodiseng.commonutils.R;
import com.saodiseng.eduservice.entity.EduCourse;
import com.saodiseng.eduservice.entity.EduTeacher;
import com.saodiseng.eduservice.service.EduCourseService;
import com.saodiseng.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/teacherfront")
@CrossOrigin//RestController 作用一个是交给springboot进行管理，一个是能够返回JSON数据
public class TeacherFrontController {
    @Autowired
    private EduTeacherService teacherService;

    @Autowired
    private EduCourseService courseService;

    //分页查询讲师的方法
    @PostMapping("getTeacherFrontList/{page}/{limit}")
    public R getTeacherFrontList (@PathVariable long page, @PathVariable long limit) {
        Page<EduTeacher> teacherPage = new Page<>(page,limit);
        Map<String,Object> map = teacherService.getTeacherFrontList(teacherPage);
        return R.ok().data(map);
    }

    //讲师详情
    @GetMapping("getTeacherFrontInfo/{teacherId}")
    public R getTeacherFrontInfo(@PathVariable String teacherId){
        //根据讲师id查询讲师基本信息
        EduTeacher eduTeacher = teacherService.getById(teacherId);
        //根据讲师id查询课程信息
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id",teacherId);
        List<EduCourse> eduCourseList = courseService.list(wrapper);
        return R.ok().data("teacher",eduTeacher).data("courseList",eduCourseList);
    }
}

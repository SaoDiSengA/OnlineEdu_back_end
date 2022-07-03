package com.saodiseng.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.saodiseng.commonutils.R;
import com.saodiseng.eduservice.entity.EduCourse;
import com.saodiseng.eduservice.entity.EduTeacher;
import com.saodiseng.eduservice.service.EduCourseService;
import com.saodiseng.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/eduservice/indexfront")
public class IndexFrontController {

    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduTeacherService teacherService;

    //查询前八条热门课程，查询前4名老师
    @GetMapping("index")
    public R index() {
        //查询前8热门课程
        // 根据id进行排序，显示排列后的前两条记录
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        //last方法，拼接sql语句
        wrapper.last("limit 8");
        List<EduCourse> eduCourseslist = courseService.list(wrapper);

        // 根据id进行排序，显示排列后的前两条记录
        QueryWrapper<EduTeacher> wrapper1 = new QueryWrapper<>();
        wrapper1.orderByDesc("id");
        //last方法，拼接sql语句
        wrapper1.last("limit 4");
        List<EduTeacher> eduTeacherslist = teacherService.list(wrapper1);

        return R.ok().data("eduCourseslist",eduCourseslist).data("eduTeacherslist",eduTeacherslist);
    }
}

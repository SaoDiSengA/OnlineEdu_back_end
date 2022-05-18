package com.saodiseng.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.saodiseng.commonutils.R;
import com.saodiseng.eduservice.entity.EduCourse;
import com.saodiseng.eduservice.entity.EduTeacher;
import com.saodiseng.eduservice.entity.chapter.ChapterVo;
import com.saodiseng.eduservice.entity.frontVo.CourseFrontVo;
import com.saodiseng.eduservice.entity.frontVo.CourseWebVo;
import com.saodiseng.eduservice.service.EduChapterService;
import com.saodiseng.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/coursefront")
@CrossOrigin//RestController 作用一个是交给springboot进行管理，一个是能够返回JSON数据
public class CourseFrontController {
    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduChapterService chapterService;
    //条件查询带分页查询课程
    @PostMapping("getFrontCourseList/{page}/{limit}")
    public R getFrontCourseList (@PathVariable long page, @PathVariable long limit, @RequestBody(required = false) CourseFrontVo courseFrontVo) {
        Page<EduCourse> coursePage = new Page<>(page,limit);
        Map<String,Object> map = courseService.getCourseFrontList(coursePage,courseFrontVo);
        return R.ok().data(map);
    }

    //课程详情方法
    @GetMapping("getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable String courseId){
        //根据课程id，编写sql语句查询课程信息
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);
        //根据id查章节小节 ，之前写过 直接注入调用即可
        List<ChapterVo> chapterVideoList = chapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("courseWebVo",courseWebVo).data("chapterVideoList",chapterVideoList);
    }
}

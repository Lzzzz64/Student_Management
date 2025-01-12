package com.example.controller.user;

import com.example.pojo.PageResult;
import com.example.pojo.Result;
import com.example.pojo.dto.CoursePageQueryDTO;
import com.example.pojo.dto.StudentCoursePageQueryDTO;
import com.example.pojo.entity.StudentCourse;
import com.example.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("userCourseController")
@RequestMapping("/user/course")
@Slf4j
public class CourseController {
    @Autowired
    private CourseService courseService;

    /**
     * 分页查询
     * @param coursePageQueryDTO
     * @return
     */
    @GetMapping("/page")
    public Result pageQuery(CoursePageQueryDTO coursePageQueryDTO){
        log.info("分页查询：{}",coursePageQueryDTO);
        PageResult pageResult = courseService.pageQuery(coursePageQueryDTO);
        return Result.success(pageResult);
    }
}

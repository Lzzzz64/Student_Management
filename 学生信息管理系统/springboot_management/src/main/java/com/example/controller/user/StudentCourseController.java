package com.example.controller.user;

import com.example.pojo.PageResult;
import com.example.pojo.Result;
import com.example.pojo.dto.StudentCoursePageQueryDTO;
import com.example.pojo.entity.StudentCourse;
import com.example.service.StudentCourseSerive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/studentCourse")
@Slf4j
public class StudentCourseController {
    @Autowired
    private StudentCourseSerive studentCourseSerive;
    /**
     * 根据sno分页查询
     * @param studentCoursePageQueryDTO
     * @return
     */
    @GetMapping("/pageBySno")
    public Result pageQueryBySno(StudentCoursePageQueryDTO studentCoursePageQueryDTO){
        log.info("分页查询：{}",studentCoursePageQueryDTO);
        PageResult pageResult = studentCourseSerive.pageQueryBySno(studentCoursePageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 选课
     * @param studentCourse
     * @return
     */
    @PostMapping("/choose")
    public Result chooseCourse(@RequestBody StudentCourse studentCourse) {
        boolean flag = studentCourseSerive.chooseCourse(studentCourse);
        if(flag) return Result.success();
        else return Result.error("选课失败");
    }

    /**
     * 退课
     * @param studentCourse
     * @return
     */
    @PostMapping("/out")
    public Result outCourse(@RequestBody StudentCourse studentCourse) {
        boolean flag = studentCourseSerive.outCourse(studentCourse);
        if(flag) return Result.success();
        else return Result.error("选课失败");
    }
}

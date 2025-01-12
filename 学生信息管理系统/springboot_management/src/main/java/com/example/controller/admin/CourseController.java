package com.example.controller.admin;

import com.example.pojo.PageResult;
import com.example.pojo.dto.CoursePageQueryDTO;
import com.example.pojo.Result;
import com.example.pojo.entity.Course;
import com.example.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/admin/course")
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
    /**
     * 修改课程
     * @param Course
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody Course Course){
        log.info("修改课程：{}",Course);
        boolean flag = courseService.update(Course);
        if(flag) return Result.success();
        else return Result.error("修改信息有误");
    }

    /**
     * 新增课程
     * @param course
     * @return
     */
    @PostMapping
    public Result save(@RequestBody Course course){
        log.info("新增课程：{}",course);
        boolean flag = courseService.save(course);
        if(flag) return Result.success();
        else return Result.error("新增失败");
    }

    /**
     * 删除课程
     * @param cno
     * @return
     */
    @DeleteMapping
    public Result deleteById(String cno){
        log.info("删除课程：{}", cno);
        boolean flag = courseService.deleteById(cno);
        if(flag) return Result.success();
        else return Result.error("删除失败");
    }
}

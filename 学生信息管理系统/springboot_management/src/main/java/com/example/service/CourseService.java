package com.example.service;

import com.example.pojo.PageResult;
import com.example.pojo.dto.CoursePageQueryDTO;
import com.example.pojo.dto.StudentCoursePageQueryDTO;
import com.example.pojo.entity.Course;
import com.example.pojo.entity.StudentCourse;

public interface CourseService {

    /**
     * 分页查询
     * @param coursePageQueryDTO
     * @return
     */
    PageResult pageQuery(CoursePageQueryDTO coursePageQueryDTO);

    /**
     * 新增课程
     * @param courseDTO
     * @return
     */
    boolean save(Course courseDTO);

    /**
     * 修改课程
     * @param course
     * @return
     */
    boolean update(Course course);

    /**
     * 删除课程
     * @param cno
     * @return
     */
    boolean deleteById(String cno);
}

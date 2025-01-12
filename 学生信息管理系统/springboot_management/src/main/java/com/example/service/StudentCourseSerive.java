package com.example.service;

import com.example.pojo.PageResult;
import com.example.pojo.dto.StudentCoursePageQueryDTO;
import com.example.pojo.entity.StudentCourse;

public interface StudentCourseSerive {
    /**
     * 根据sno分页查询
     * @param studentCoursePageQueryDTO
     * @return
     */
    PageResult pageQueryBySno(StudentCoursePageQueryDTO studentCoursePageQueryDTO);

    /**
     * 选课
     * @param studentCourse
     * @return
     */
    boolean chooseCourse(StudentCourse studentCourse);

    /**
     * 退课
     * @param studentCourse
     * @return
     */
    boolean outCourse(StudentCourse studentCourse);
}

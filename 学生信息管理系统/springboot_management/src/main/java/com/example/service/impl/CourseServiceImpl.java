package com.example.service.impl;

import com.example.mapper.CourseMapper;
import com.example.mapper.StudentCourseMapper;
import com.example.mapper.TeacherMapper;
import com.example.pojo.PageResult;
import com.example.pojo.dto.CoursePageQueryDTO;
import com.example.pojo.dto.StudentCoursePageQueryDTO;
import com.example.pojo.entity.Course;
import com.example.pojo.entity.StudentCourse;
import com.example.pojo.entity.Teacher;
import com.example.service.CourseService;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import jdk.nashorn.internal.runtime.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private StudentCourseMapper studentCourseMapper;
    /**
     * 分页查询
     * @param coursePageQueryDTO
     * @return
     */
    public PageResult pageQuery(CoursePageQueryDTO coursePageQueryDTO) {
        PageHelper.startPage(coursePageQueryDTO.getPage(),coursePageQueryDTO.getPageSize());
        Page<Course> page = courseMapper.pageQuery(coursePageQueryDTO);
        return new PageResult(page.getTotal(),page.getResult());
    }

    /**
     * 新增课程
     * @param course
     * @return
     */
    public boolean save(Course course) {
        if(course.getTeacher_tno() != null && course.getTeacher_tno() != "") {
            Teacher teacher = teacherMapper.selectById(course.getTeacher_tno());
            //此添加课程中的教师号不存在
            if(teacher == null) return false;
        }

        courseMapper.insert(course);

        return true;
    }

    /**
     * 修改课程
     * @param course
     * @return
     */
    public boolean update(Course course) {
        if(course.getTeacher_tno() != null && course.getTeacher_tno() != "") {
            Teacher teacher = teacherMapper.selectById(course.getTeacher_tno());
            //此添加课程中的教师号不存在
            if(teacher == null) return false;
        }

        courseMapper.update(course);

        return true;
    }

    /**
     * 删除课程
     * @param cno
     * @return
     */
    public boolean deleteById(String cno) {
        courseMapper.deleteById(cno);

        //删除学生选中该课程的数据
        studentCourseMapper.deleteByCno(cno);
        return true;
    }
}

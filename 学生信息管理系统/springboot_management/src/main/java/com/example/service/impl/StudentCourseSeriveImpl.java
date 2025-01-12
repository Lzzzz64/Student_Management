package com.example.service.impl;

import com.example.mapper.CourseMapper;
import com.example.mapper.StudentCourseMapper;
import com.example.pojo.PageResult;
import com.example.pojo.dto.StudentCoursePageQueryDTO;
import com.example.pojo.entity.Course;
import com.example.pojo.entity.StudentCourse;
import com.example.service.StudentCourseSerive;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentCourseSeriveImpl implements StudentCourseSerive {
    @Autowired
    private StudentCourseMapper studentCourseMapper;
    @Autowired
    private CourseMapper courseMapper;
    /**
     * 根据sno分页查询
     * @param studentCoursePageQueryDTO
     * @return
     */
    public PageResult pageQueryBySno(StudentCoursePageQueryDTO studentCoursePageQueryDTO) {
        PageHelper.startPage(studentCoursePageQueryDTO.getPage(),studentCoursePageQueryDTO.getPageSize());
        Page<Course> page = studentCourseMapper.pageQueryBySno(studentCoursePageQueryDTO);
        return new PageResult(page.getTotal(),page.getResult());
    }

    /**
     * 选课
     * @param studentCourse
     * @return
     */
    public boolean chooseCourse(StudentCourse studentCourse) {
        String cno = studentCourse.getCno();
        String sno = studentCourse.getSno();

        //判断该学生是否已经选过该课，选过则退出
        StudentCourse sc = studentCourseMapper.getBySnoAndCno(studentCourse);
        if(sc != null) return false;

        //剩余人数小于1，选课失败
        Course course = courseMapper.selectByCno(cno);
        if(course.getNumber() <= 0) return false;

        //插入该课程
        studentCourseMapper.insert(studentCourse);
        //剩余选课人数减一
        courseMapper.reduce(cno);
        return true;
    }

    /**
     * 退课
     * @param studentCourse
     * @return
     */
    public boolean outCourse(StudentCourse studentCourse) {
        String cno = studentCourse.getCno();
        String sno = studentCourse.getSno();

        //判断该学生是否已经选过该课,没有选则退出
        StudentCourse sc = studentCourseMapper.getBySnoAndCno(studentCourse);
        if(sc == null) return false;

        //删除该课程
        studentCourseMapper.deleteBySnoAndCno(studentCourse);
        //剩余选课人数加一
        courseMapper.add(cno);
        return true;
    }
}

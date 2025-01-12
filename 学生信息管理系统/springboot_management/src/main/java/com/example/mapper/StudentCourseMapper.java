package com.example.mapper;

import com.example.pojo.dto.StudentCoursePageQueryDTO;
import com.example.pojo.entity.Administrator;
import com.example.pojo.entity.Course;
import com.example.pojo.entity.StudentCourse;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentCourseMapper {
    /**
     * 根据学号获取该学生的选课
     * @return
     */
    @Select("select * from student_course where sno = #{sno}")
    List<StudentCourse> getCoursesBySno(String sno);

    /**
     * 根据学号和课程号获取信息
     * @param studentCourse
     * @return
     */
    @Select("select * from student_course where sno =#{sno} and cno = #{cno}")
    StudentCourse getBySnoAndCno(StudentCourse studentCourse);

    /**
     * 新增学生选课信息
     * @param studentCourse
     */
    @Insert("insert into student_course (sno, cno) values (#{sno}, #{cno})")
    void insert(StudentCourse studentCourse);

    /**
     * 删除学号为sno学生的选课
     * @param sno
     */
    @Delete("delete from student_course where sno = #{sno}")
    void deleteBySno(String sno);

    /**
     * 删除学号为sno学生的选课
     * @param cno
     */
    @Delete("delete from student_course where cno = #{cno}")
    void deleteByCno(String cno);

    /**
     * 根据sno分页查询
     * @param studentCoursePageQueryDTO
     * @return
     */
    @Select("select a.cno, a.cn, a.score, a.time, a.teacher_tno,a.classroom, a.number from course as a, student_course as b where a.cno = b.cno and b.sno = #{sno}")
    Page<Course> pageQueryBySno(StudentCoursePageQueryDTO studentCoursePageQueryDTO);

    /**
     * 根据sno和cno删除
     * @param studentCourse
     */
    @Delete("delete from student_course where cno = #{cno} and sno = #{sno}")
    void deleteBySnoAndCno(StudentCourse studentCourse);
}

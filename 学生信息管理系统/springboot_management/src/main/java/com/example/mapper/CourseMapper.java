package com.example.mapper;

import com.example.pojo.dto.CoursePageQueryDTO;
import com.example.pojo.dto.StudentCoursePageQueryDTO;
import com.example.pojo.entity.Course;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CourseMapper {
    /**
     * 分页查询
     * @param coursePageQueryDTO
     * @return
     */
    Page<Course> pageQuery(CoursePageQueryDTO coursePageQueryDTO);

    /**
     * 添加课程
     * @param course
     */
    @Insert("insert into course values(#{cno}, #{cn}, #{score}, #{time}, #{classroom}, #{teacher_tno},#{number})")
    void insert(Course course);

    /**
     * 修改课程
     * @param course
     */
    void update(Course course);

    /**
     * 删除课程
     * @param cno
     */
    @Delete("delete from course where cno = #{cno}")
    void deleteById(String cno);

    /**
     * 根据cno查询课程
     * @param cno
     * @return
     */
    @Select("select * from course where cno = #{cno}")
    Course selectByCno(String cno);

    /**
     * 剩余人数减一
     * @param cno
     */
    @Update("update course set number = number-1 where cno = #{cno}")
    void reduce(String cno);

    /**
     * 剩余人数减一
     * @param cno
     */
    @Update("update course set number = number+1 where cno = #{cno}")
    void add(String cno);

}

package com.example.mapper;

import com.example.pojo.dto.StudentLoginDTO;
import com.example.pojo.dto.StudentPageQueryDTO;
import com.example.pojo.entity.Dormitory;
import com.example.pojo.entity.Student;
import com.example.pojo.vo.StudentVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {
    /**
     * 分页查询
     * @param studentPageQueryDTO
     * @return
     */
    Page<StudentVO> pageQuery(StudentPageQueryDTO studentPageQueryDTO);

    /**
     * 修改学生信息
     * @param student
     */
    void update(Student student);

    /**
     * 删除分类
     * @param sno
     * @return
     */
    @Delete("delete from student where sno = #{sno}")
    void delete(String sno);

    /**
     * 根据sno查询学生数据
     */
    @Select("select * from student where sno = #{sno}")
    Student selectBySno(String sno);

    /**
     * 新增学生
     * @param student
     */
    @Insert("insert into student (sno,sn,gender,maj,dormitory_dno) values (#{sno},#{sn},#{gender},#{maj},#{dormitory_dno})")
    void insert(Student student);

    /**
     * 根据sno和sn查询学生
     * @param studentLoginDTO
     * @return
     */
    @Select("select * from student where sno = #{sno} and sn = #{sn}")
    Student login(StudentLoginDTO studentLoginDTO);
}

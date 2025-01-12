package com.example.mapper;

import com.example.pojo.dto.TeacherDTO;
import com.example.pojo.dto.TeacherPageQueryDTO;
import com.example.pojo.entity.Teacher;
import com.example.pojo.vo.TeacherVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TeacherMapper {

    /**
     * 分页查询
     * @param teacherPageQueryDTO
     * @return
     */
    Page<TeacherVO> pageQuery(TeacherPageQueryDTO teacherPageQueryDTO);

    /**
     * 新增教师
     * @param teacherDTO
     */
    @Insert("insert into teacher (tno,tn,gender,office) values (#{tno}, #{tn}, #{gender}, #{office})")
    void insert(TeacherDTO teacherDTO);

    /**
     * 修改教师
     * @param teacherDTO
     */
    void update(TeacherDTO teacherDTO);

    /**
     * 删除教师
     * @param tno
     */
    @Delete("delete from teacher where tno = #{tno}")
    void deleteById(String tno);

    /**
     * 根据tno查询教师
     * @param tno
     * @return
     */
    @Select("select * from teacher where tno = #{tno}")
    Teacher selectById(String tno);
}

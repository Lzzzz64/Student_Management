package com.example.service;

import com.example.pojo.PageResult;
import com.example.pojo.dto.StudentDTO;
import com.example.pojo.dto.StudentPageQueryDTO;
import com.example.pojo.dto.TeacherDTO;
import com.example.pojo.dto.TeacherPageQueryDTO;
import com.example.pojo.entity.Teacher;

public interface TeacherService {
    /**
     * 分页查找
     * @param teacherPageQueryDTO
     * @return
     */
    PageResult pageQuery(TeacherPageQueryDTO teacherPageQueryDTO);

    /**
     * 新增教师
     * @param teacherDTO
     */
    void save(TeacherDTO teacherDTO);

    /**
     * 修改教师
     * @param teacherDTO
     */
    void update(TeacherDTO teacherDTO);

    /**
     * 删除教师
     * @param tno
     */
    void deleteById(String tno);
}

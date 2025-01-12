package com.example.service;

import com.example.pojo.PageResult;
import com.example.pojo.dto.StudentDTO;
import com.example.pojo.dto.StudentLoginDTO;
import com.example.pojo.dto.StudentPageQueryDTO;
import com.example.pojo.entity.Student;
import com.example.pojo.vo.StudentVO;

import java.util.List;

public interface StudentService {
    /**
     * 分页查找
     * @param studentPageQueryDTO
     * @return
     */
    PageResult pageQuery(StudentPageQueryDTO studentPageQueryDTO);

    /**
     * 修改学生
     * @param studentDTO
     */
    boolean update(StudentDTO studentDTO);

    /**
     * 删除学生
     * @param sno
     * @return
     */
    void deleteById(String sno);

    /**
     * 新增学生
     * @param studentDTO
     */
    boolean save(StudentDTO studentDTO);

    /**
     * 学生登录
     * @param studentLoginDTO
     * @return
     */
    boolean login(StudentLoginDTO studentLoginDTO);

    /**
     * 获取学生信息
     * @param sno
     * @return
     */
    StudentVO information(String sno);
}

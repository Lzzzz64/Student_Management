package com.example.service.impl;


import com.example.mapper.DormitoryMapper;
import com.example.mapper.StudentCourseMapper;
import com.example.mapper.StudentMapper;
import com.example.pojo.PageResult;
import com.example.pojo.dto.StudentDTO;
import com.example.pojo.dto.StudentLoginDTO;
import com.example.pojo.dto.StudentPageQueryDTO;
import com.example.pojo.entity.Dormitory;
import com.example.pojo.entity.Student;
import com.example.pojo.vo.StudentVO;
import com.example.service.DormitoryService;
import com.example.service.StudentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private DormitoryMapper dormitoryMapper;
    @Autowired
    private StudentCourseMapper studentCourseMapper;
    /**
     * 分页查询
     * @param studentPageQueryDTO
     * @return
     */
    public PageResult pageQuery(StudentPageQueryDTO studentPageQueryDTO) {
        PageHelper.startPage(studentPageQueryDTO.getPage(),studentPageQueryDTO.getPageSize());
        Page<StudentVO> page = studentMapper.pageQuery(studentPageQueryDTO);
        return new PageResult(page.getTotal(),page.getResult());
    }

    /**
     * 修改学生
     * @param studentDTO
     */

    public boolean update(StudentDTO studentDTO) {
        Student student = new Student();
        String address = studentDTO.getAddress();

        String dormitory_dno_now = "";
        String dormitory_dno_front = "";

        dormitory_dno_front = studentMapper.selectBySno(studentDTO.getSno()).getDormitory_dno();

        //查找是否存在该宿舍地址
        if(address != null && address != "") {
            Dormitory dormitory = dormitoryMapper.selectByAddress(address);
            if(dormitory == null) return false;
            dormitory_dno_now = dormitory.getDno();
        }

        BeanUtils.copyProperties(studentDTO,student);
        student.setDormitory_dno(dormitory_dno_now);

        //将修改之前宿舍剩余人数加一
        if(dormitory_dno_front != null && dormitory_dno_front != "") dormitoryMapper.add(dormitory_dno_front);

        //将修改之后宿舍剩余人数减一
        if(dormitory_dno_now != null && dormitory_dno_now != "") {
            Dormitory dormitory = dormitoryMapper.selectByDno(dormitory_dno_now);
            if(dormitory.getNumber() == 0) return false;
            dormitoryMapper.reduce(dormitory_dno_now);
        }

        studentMapper.update(student);
        return true;
    }

    /**
     * 删除分类
     * @param sno
     * @return
     */
    public void deleteById(String sno) {
        String dormitory_dno = studentMapper.selectBySno(sno).getDormitory_dno();

        //判断是否有宿舍
        if(dormitory_dno != null && dormitory_dno != "") {
            //有宿舍的，将宿舍剩余人数加一
            dormitoryMapper.add(dormitory_dno);
        }

        //删除该学生的选课
        studentCourseMapper.deleteBySno(sno);

        studentMapper.delete(sno);
    }

    /**
     * 新增学生
     * @param studentDTO
     */
    public boolean save(StudentDTO studentDTO) {
        Student student = new Student();
        BeanUtils.copyProperties(studentDTO, student);

        //判断对象中是否有添加了宿舍地址
        if (studentDTO.getAddress() != ""){
            //判断宿舍地址是否存在
            Dormitory dormitory = dormitoryMapper.selectByAddress(studentDTO.getAddress());
            if(dormitory == null || dormitory.getNumber()==0) return false;

            //存在则将该宿舍剩余人数减一
            student.setDormitory_dno(dormitory.getDno());
            dormitoryMapper.reduce(dormitory.getDno());
        }
        studentMapper.insert(student);

        return true;
    }

    /**
     * 学生登录
     * @param studentLoginDTO
     * @return
     */
    public boolean login(StudentLoginDTO studentLoginDTO) {
        Student student = studentMapper.login(studentLoginDTO);
        if(student != null) return true;
        return false;
    }

    /**
     * 获取学生信息
     * @param sno
     * @return
     */
    public StudentVO information(String sno) {
        Student student = studentMapper.selectBySno(sno);
        StudentVO studentVO = new StudentVO();
        BeanUtils.copyProperties(student,studentVO);

        Dormitory dormitory = dormitoryMapper.selectByDno(student.getDormitory_dno());
        studentVO.setAddress(dormitory.address);

        return studentVO;
    }
}

package com.example.service.impl;

import com.example.mapper.DormitoryMapper;
import com.example.mapper.StudentMapper;
import com.example.mapper.TeacherMapper;
import com.example.pojo.PageResult;
import com.example.pojo.dto.StudentDTO;
import com.example.pojo.dto.StudentPageQueryDTO;
import com.example.pojo.dto.TeacherDTO;
import com.example.pojo.dto.TeacherPageQueryDTO;
import com.example.pojo.entity.Dormitory;
import com.example.pojo.entity.Student;
import com.example.pojo.entity.Teacher;
import com.example.pojo.vo.StudentVO;
import com.example.pojo.vo.TeacherVO;
import com.example.service.DormitoryService;
import com.example.service.StudentService;
import com.example.service.TeacherService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    /**
     * 分页查询
     * @param teacherPageQueryDTO
     * @return
     */
    public PageResult pageQuery(TeacherPageQueryDTO teacherPageQueryDTO) {
        PageHelper.startPage(teacherPageQueryDTO.getPage(),teacherPageQueryDTO.getPageSize());
        Page<TeacherVO> page = teacherMapper.pageQuery(teacherPageQueryDTO);
        return new PageResult(page.getTotal(),page.getResult());
    }

    /**
     * 新增教师
     * @param teacherDTO
     */
    public void save(TeacherDTO teacherDTO) {
        teacherMapper.insert(teacherDTO);
    }

    /**
     * 修改教师
     * @param teacherDTO
     */
    public void update(TeacherDTO teacherDTO) {
        teacherMapper.update(teacherDTO);
    }

    /**
     * 删除教师
     * @param tno
     */
    public void deleteById(String tno) {
        teacherMapper.deleteById(tno);
    }
}

package com.example.service.impl;

import com.example.mapper.DormitoryMapper;
import com.example.mapper.StudentMapper;
import com.example.pojo.PageResult;
import com.example.pojo.dto.ChooseDormitoryDTO;
import com.example.pojo.dto.DormitoryPageQueryDTO;
import com.example.pojo.entity.Dormitory;
import com.example.pojo.entity.Student;
import com.example.service.DormitoryService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DormitoryServiceImpl implements DormitoryService {
    @Autowired
    private DormitoryMapper dormitoryMapper;
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 分页查询宿舍
     * @param dormitoryPageQueryDTO
     * @return
     */
    public PageResult pageQuery(DormitoryPageQueryDTO dormitoryPageQueryDTO) {
        PageHelper.startPage(dormitoryPageQueryDTO.getPage(),dormitoryPageQueryDTO.getPageSize());
        Page<Dormitory> page = dormitoryMapper.pageQuery(dormitoryPageQueryDTO);
        return new PageResult(page.getTotal(),page.getResult());
    }

    /**
     * 新增宿舍
     * @param dormitory
     */
    public void save(Dormitory dormitory) {
       dormitoryMapper.insert(dormitory);
    }

    /**
     * 删除分类
     * @param dno
     * @return
     */
    public void deleteById(String dno) {
       dormitoryMapper.deleteById(dno);
    }

    /**
     * 更新宿舍
     * @param dormitory
     */
    public void update(Dormitory dormitory) {
        dormitoryMapper.update(dormitory);
    }

    /**
     * 宿舍剩余人数加一
     * @param dno
     */
    public void addOne(String dno){
        Dormitory dormitory_front = dormitoryMapper.selectByDno(dno);
        dormitory_front.setNumber(dormitory_front.getNumber()+1);
        dormitoryMapper.update(dormitory_front);
    }

    /**
     * 宿舍剩余人数减一
     * @param dno
     */
    public boolean reduceOne(String dno){
        Dormitory dormitory_now = dormitoryMapper.selectByDno(dno);
        //如果修改的宿舍剩余人数为0
        if(dormitory_now.getNumber() == 0) return false;

        dormitory_now.setNumber(dormitory_now.getNumber()-1);
        dormitoryMapper.update(dormitory_now);

        return true;
    }

    /**
     * 根据宿舍地址判断是否有该宿舍
     * @param address
     */
    public boolean isHaveAddress(String address){
        Dormitory dormitory = dormitoryMapper.selectByAddress(address);
        if(dormitory == null) return false;
        else return true;
    }

    /**
     * 获取所有宿舍地址
     * @return
     */
    public List<String> list() {
        List<String> list = dormitoryMapper.list();
        return list;
    }

    /**
     * 选宿舍
     * @param chooseDormitoryDTO
     * @return
     */
    public boolean chooseCourse(ChooseDormitoryDTO chooseDormitoryDTO) {
        String sno = chooseDormitoryDTO.getSno();
        String dno = chooseDormitoryDTO.getDno();

        //判断该学生是否已经选过宿舍
        Student student = studentMapper.selectBySno(sno);
        if(student.getDormitory_dno() != null && student.getDormitory_dno() != "") return false;

        //将宿舍添加到学生信息中
        student.setDormitory_dno(dno);
        System.out.println(student);
        studentMapper.update(student);

        //宿舍剩余人数减一
        dormitoryMapper.reduce(dno);

        return true;
    }
}

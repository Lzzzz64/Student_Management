package com.example.service;

import com.example.pojo.PageResult;
import com.example.pojo.dto.ChooseDormitoryDTO;
import com.example.pojo.dto.DormitoryPageQueryDTO;
import com.example.pojo.entity.Dormitory;

import java.util.List;

public interface DormitoryService {
    /**
     * 分页查询宿舍
     * @return
     */
    PageResult pageQuery(DormitoryPageQueryDTO dormitoryPageQueryDTO);

    /**
     * 新增宿舍
     * @param dormitory
     */
    void save(Dormitory dormitory);

    /**
     * 删除宿舍
     * @param dno
     * @return
     */
    void deleteById(String dno);

    /**
     * 更新宿舍
     * @param dormitory
     */
    void update(Dormitory dormitory);

    /**
     * 宿舍剩余人数加一
     * @param dno
     */
    public void addOne(String dno);

    /**
     * 宿舍剩余人数减一
     * @param dno
     */
    public boolean reduceOne(String dno);

    /**
     * 根据宿舍地址判断是否有该宿舍
     * @param address
     */
    public boolean isHaveAddress(String address);

    /**
     * 获取所有宿舍地址
     * @return
     */
    List<String> list();

    /**
     * 选择宿舍
     * @param chooseDormitoryDTO
     * @return
     */
    boolean chooseCourse(ChooseDormitoryDTO chooseDormitoryDTO);
}

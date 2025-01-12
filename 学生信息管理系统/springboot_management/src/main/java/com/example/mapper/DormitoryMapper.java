package com.example.mapper;

import com.example.pojo.dto.DormitoryPageQueryDTO;
import com.example.pojo.entity.Dormitory;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DormitoryMapper {
    /**
     * 分页查询
     * @param dormitoryPageQueryDTO
     * @return
     */
    Page<Dormitory> pageQuery(DormitoryPageQueryDTO dormitoryPageQueryDTO);

    /**
     * 新增宿舍
     * @param dormitory
     */
    @Insert("insert into dormitory values (#{dno}, #{address}, #{number})")
    void insert(Dormitory dormitory);

    /**
     * 删除分类
     * @param dno
     * @return
     */
    @Delete("delete from dormitory where dno = #{dno}")
    void deleteById(String dno);

    /**
     * 更新宿舍
     * @param dormitory
     */
    void update(Dormitory dormitory);

    /**
     * 根据宿舍地址查询宿舍
     */
    @Select("select * from dormitory where address = #{address}")
    Dormitory selectByAddress(String address);

    /**
     * 根据宿舍号查询宿舍
     * @param dno
     * @return
     */
    @Select("select * from dormitory where dno = #{dno}")
    Dormitory selectByDno(String dno);

    /**
     * 剩余人数减一
     * @param dno
     */
    @Update("update dormitory set number = number-1 where dno = #{dno}")
    void reduce(String dno);

    /**
     * 剩余人数加一
     * @param dno
     */
    @Update("update dormitory set number = number+1 where dno = #{dno}")
    void add(String dno);

    /**
     * 获取所有宿舍地址
     * @return
     */
    @Select("select address from dormitory")
    List<String> list();

}

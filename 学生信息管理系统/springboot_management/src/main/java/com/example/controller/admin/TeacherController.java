package com.example.controller.admin;

import com.example.pojo.PageResult;
import com.example.pojo.Result;
import com.example.pojo.dto.DormitoryPageQueryDTO;
import com.example.pojo.dto.TeacherDTO;
import com.example.pojo.dto.TeacherPageQueryDTO;
import com.example.pojo.entity.Dormitory;
import com.example.service.DormitoryService;
import com.example.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/teacher")
@Slf4j
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    /**
     * 分页查询教师
     * @param teacherPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    public Result pageQuery(TeacherPageQueryDTO teacherPageQueryDTO){
        log.info("分页查询：{}",teacherPageQueryDTO);
        PageResult pageResult = teacherService.pageQuery(teacherPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 修改教师
     * @param teacherDTO
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody TeacherDTO teacherDTO){
        log.info("修改教师：{}",teacherDTO);
        teacherService.update(teacherDTO);
        return Result.success();
    }

    /**
     * 新增教师
     * @param teacherDTO
     * @return
     */
    @PostMapping
    public Result save(@RequestBody TeacherDTO teacherDTO){
        log.info("新增教师：{}",teacherDTO);
        teacherService.save(teacherDTO);
        return Result.success();
    }

    /**
     * 删除分类
     * @param tno
     * @return
     */
    @DeleteMapping
    public Result deleteById(String tno){
        log.info("删除教师：{}", tno);
        teacherService.deleteById(tno);
        return Result.success();
    }
}

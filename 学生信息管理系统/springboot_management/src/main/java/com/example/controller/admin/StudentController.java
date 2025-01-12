package com.example.controller.admin;

import com.example.pojo.PageResult;
import com.example.pojo.Result;
import com.example.pojo.dto.DormitoryPageQueryDTO;
import com.example.pojo.dto.StudentDTO;
import com.example.pojo.dto.StudentPageQueryDTO;
import com.example.pojo.entity.Dormitory;
import com.example.pojo.entity.Student;
import com.example.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/student")
@Slf4j
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * 分页查询学生
     * @param studentPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    public Result pageQuery(StudentPageQueryDTO studentPageQueryDTO){
        log.info("分页查询：{}",studentPageQueryDTO);
        PageResult pageResult = studentService.pageQuery(studentPageQueryDTO);
        return Result.success(pageResult);
    }
    /**
     * 修改学生
     * @param studentDTO
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody StudentDTO studentDTO){
        log.info("修改学生：{}",studentDTO);
        boolean flag = studentService.update(studentDTO);
        if(flag) return Result.success();
        else return Result.error("修改信息有误");
    }

    /**
     * 新增学生
     * @param studentDTO
     * @return
     */
    @PostMapping
    public Result save(@RequestBody StudentDTO studentDTO){
        log.info("新增学生：{}",studentDTO);
        boolean flag = studentService.save(studentDTO);
        if(flag) return Result.success();
        else return Result.error("新增失败");
    }

    /**
     * 删除分类
     * @param sno
     * @return
     */
    @DeleteMapping
    public Result deleteById(String sno){
        log.info("删除学生：{}", sno);
        studentService.deleteById(sno);
        return Result.success();
    }
}

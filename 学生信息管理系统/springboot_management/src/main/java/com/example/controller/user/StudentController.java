package com.example.controller.user;

import com.example.pojo.PageResult;
import com.example.pojo.Result;
import com.example.pojo.dto.StudentCoursePageQueryDTO;
import com.example.pojo.dto.StudentLoginDTO;
import com.example.pojo.entity.Student;
import com.example.pojo.vo.StudentVO;
import com.example.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("userStudentController")
@RequestMapping("/user/student")
@Slf4j
public class StudentController {
    @Autowired
    private StudentService studentService;

    public String sno;
    /**
     * 学生登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody StudentLoginDTO studentLoginDTO) {
        log.info("学生登录：{}",studentLoginDTO);
        boolean flag = studentService.login(studentLoginDTO);
        if(flag) {
            return Result.success();
        }
        else return Result.error("账号密码错误");
    }

    /**
     * 获取学生信息
     * @param sno
     * @return
     */
    @GetMapping
    public Result information(String sno) {
        log.info("学生信息：{}",sno);
        StudentVO studentvo = studentService.information(sno);
        return Result.success(studentvo);
    }
}

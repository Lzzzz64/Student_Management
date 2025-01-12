package com.example.controller.user;

import com.example.pojo.PageResult;
import com.example.pojo.Result;
import com.example.pojo.dto.ChooseDormitoryDTO;
import com.example.pojo.dto.DormitoryPageQueryDTO;
import com.example.service.DormitoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("userDormitoryController")
@RequestMapping("/user/dormitory")
@Slf4j
public class DormitoryController {
    @Autowired
    private DormitoryService dormitoryService;

    /**
     * 分页查询宿舍
     * @param dormitoryPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    public Result pageQuery(DormitoryPageQueryDTO dormitoryPageQueryDTO){
        log.info("分页查询：{}",dormitoryPageQueryDTO);
        PageResult pageResult = dormitoryService.pageQuery(dormitoryPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 选择宿舍
     * @return
     */
    @PostMapping
    public Result ChooseDormitory(@RequestBody ChooseDormitoryDTO chooseDormitoryDTO) {
        boolean flag = dormitoryService.chooseCourse(chooseDormitoryDTO);
        if(flag) return Result.success();
        else return Result.error("选课失败");
    }
}

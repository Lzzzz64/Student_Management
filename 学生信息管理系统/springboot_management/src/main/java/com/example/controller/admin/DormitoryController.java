package com.example.controller.admin;

import com.example.pojo.PageResult;
import com.example.pojo.Result;
import com.example.pojo.dto.DormitoryPageQueryDTO;
import com.example.pojo.entity.Dormitory;
import com.example.service.DormitoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/dormitory")
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
     * 获取所有宿舍地址
     * @return
     */
    @GetMapping
    public Result list() {
        List<String> list = dormitoryService.list();
        return Result.success(list);
    }

    /**
     * 修改宿舍
     * @param dormitory
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody Dormitory dormitory){
        log.info("修改宿舍：{}",dormitory);
        dormitoryService.update(dormitory);
        return Result.success();
    }

    /**
     * 新增宿舍
     * @param dormitory
     * @return
     */
    @PostMapping
    public Result save(@RequestBody Dormitory dormitory){
        log.info("新增宿舍：{}",dormitory);
        dormitoryService.save(dormitory);
        return Result.success();
    }

    /**
     * 删除分类
     * @param dno
     * @return
     */
    @DeleteMapping
    public Result deleteById(String dno){
        log.info("删除宿舍：{}", dno);
        dormitoryService.deleteById(dno);
        return Result.success();
    }
}

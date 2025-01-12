package com.example.controller.admin;

import com.example.pojo.Result;
import com.example.pojo.entity.Administrator;
import com.example.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/administrator")
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;
    /**
     * 登录管理员账号
     * @param administrator
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody Administrator administrator)
    {
        Boolean flag = administratorService.login(administrator);
        if(flag) return Result.success();
        else return Result.error("账号密码错误");
    }

}

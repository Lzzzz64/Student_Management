package com.example.service.impl;

import com.example.mapper.AdministratorMapper;
import com.example.pojo.entity.Administrator;
import com.example.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministratorServiceImpl implements AdministratorService {
    @Autowired
    private AdministratorMapper administratorMapper;
    /**
     * 根据账号密码查询
     * @param administrator
     * @return
     */
    public boolean login(Administrator administrator) {
        int num = administratorMapper.selectByAccountAndPwd(administrator);
        if(num != 1) return false;
        else return true;
    }
}

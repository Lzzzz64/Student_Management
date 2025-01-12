package com.example.service;

import com.example.pojo.entity.Administrator;

public interface AdministratorService {
    /**
     * 根据账号密码查询
     * @param administrator
     * @return
     */
    boolean login(Administrator administrator);
}

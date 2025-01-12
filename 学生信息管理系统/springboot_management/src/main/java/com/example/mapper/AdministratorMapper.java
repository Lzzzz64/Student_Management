package com.example.mapper;

import com.example.pojo.entity.Administrator;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdministratorMapper {
    /**
     * 根据账号密码查询管理员
     * @param administrator
     * @return
     */
    @Select("select count(*) from administrator where account = #{account} and password = #{password}")
    int selectByAccountAndPwd(Administrator administrator);
}

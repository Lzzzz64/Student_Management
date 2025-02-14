package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Result {
    private Integer code;
    private String msg;
    private Object data;

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result success(){ //增删改 成功响应
        return new Result(1,"success",null);
    }
    public static Result success(Object data){ //查询 成功响应
        return new Result(1,"success",data);
    }
    public static Result error(String msg){
        return new Result(0,msg,null);
    }
}

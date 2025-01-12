package com.example.pojo.dto;

import lombok.Data;

@Data
public class TeacherPageQueryDTO {
    private String tno;
    private String tn;
    private int page;
    private int pageSize;
}

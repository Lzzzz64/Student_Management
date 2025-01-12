package com.example.pojo.dto;

import lombok.Data;

@Data
public class StudentCoursePageQueryDTO {
    private String sno;
    private int page;
    private int pageSize;
}

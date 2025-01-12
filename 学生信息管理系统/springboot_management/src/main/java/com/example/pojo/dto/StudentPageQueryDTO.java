package com.example.pojo.dto;

import lombok.Data;

@Data
public class StudentPageQueryDTO {
    private String sno;
    private String sn;
    private int page;
    private int pageSize;
}

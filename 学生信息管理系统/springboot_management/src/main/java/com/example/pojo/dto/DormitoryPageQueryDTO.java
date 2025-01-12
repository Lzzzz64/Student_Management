package com.example.pojo.dto;

import lombok.Data;

@Data
public class DormitoryPageQueryDTO {
    private String dno;
    private String address;
    private int page;
    private int pageSize;
}

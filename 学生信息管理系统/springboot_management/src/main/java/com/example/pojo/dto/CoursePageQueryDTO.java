package com.example.pojo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CoursePageQueryDTO {
    private String cno;
    private String cn;
    private int page;
    private int pageSize;
}

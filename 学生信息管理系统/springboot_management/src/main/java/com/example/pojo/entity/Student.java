package com.example.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
public class Student {
    private String sno;
    private String sn;
    private String gender;
    private String maj;
    private String dormitory_dno;
}

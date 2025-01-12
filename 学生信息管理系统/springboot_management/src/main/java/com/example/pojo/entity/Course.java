package com.example.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Course {
    private String cno;
    private String cn;
    private double score;
    private int time;
    private String classroom;
    private String teacher_tno;
    private int number;
}

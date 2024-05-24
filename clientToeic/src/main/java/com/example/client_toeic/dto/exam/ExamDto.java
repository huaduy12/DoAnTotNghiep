package com.example.client_toeic.dto.exam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamDto {
    private Integer id;
    private String name;
    private String title;
    private String description;
    private String typeSkill;
    private String typeExam;
    private String linkImage;
    private Double price;

}

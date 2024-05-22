package com.example.do_an_toeic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionExerciseDto {
    private Integer id;
    private String name;
    private String level;
    private String skill;
    private List<QuestionApi> questions;
}

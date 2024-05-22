package com.example.do_an_toeic.dto;

import com.example.do_an_toeic.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionExerciseShowUpdateDto {
    private Integer id;
    private String name;
    private String level;
    private String skill;
    private List<Question> questions;
    private List<Question> questionsUsing;
}

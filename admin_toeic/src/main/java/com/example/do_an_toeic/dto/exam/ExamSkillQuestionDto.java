package com.example.do_an_toeic.dto.exam;

import com.example.do_an_toeic.dto.QuestionApi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamSkillQuestionDto {
    private Integer id;
    private String name;
    private String level;
    private String skill;
    private String time;
    private List<QuestionApi> questions;
}

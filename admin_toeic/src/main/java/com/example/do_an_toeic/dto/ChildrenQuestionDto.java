package com.example.do_an_toeic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChildrenQuestionDto {

    private String id;
    private String name;
    private String level;
    private String type;
    private String description;
    private Integer answerCorrect;
    private List<String> answers;
}

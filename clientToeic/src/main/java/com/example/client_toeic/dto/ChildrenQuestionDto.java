package com.example.client_toeic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChildrenQuestionDto {

    private String id;
    private String text;  // tên câu hỏi
    private String level;
    private String type;
    private String description;
    private Integer correctAnswerIndex;
    private List<String> answers;
}

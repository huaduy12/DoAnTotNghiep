package com.example.client_toeic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionPhoto {
    private Integer id;
    private String image;
    private String audioSrc;
    private String text;  // tên câu hỏi
    private String description;
    private List<String> answers;
    private Integer correctAnswerIndex;
}

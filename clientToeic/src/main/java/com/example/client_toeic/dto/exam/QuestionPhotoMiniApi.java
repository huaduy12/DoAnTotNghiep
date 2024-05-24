package com.example.client_toeic.dto.exam;

import com.example.client_toeic.dto.ChildrenQuestionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionPhotoMiniApi {
    private Integer id;
    private String image;
    private String audioSrc;
    private String text;  // tên câu hỏi
    private String description;
    private List<String> answers;
    private Integer correctAnswerIndex;
    private List<ChildrenQuestionDto> questions;
}

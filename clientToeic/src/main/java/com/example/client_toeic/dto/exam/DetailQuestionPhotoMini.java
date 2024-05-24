package com.example.client_toeic.dto.exam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailQuestionPhotoMini {
    private String part;
    private List<QuestionPhotoMiniApi> questionPhotoExamApis;
}

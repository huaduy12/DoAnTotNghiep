package com.example.do_an_toeic.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChoiceQuestionDto {
    private String levelToeic;
    private String skillToeic;
    private Integer typeQuestion;  // câu hỏi cho thi hay ôn tập

}

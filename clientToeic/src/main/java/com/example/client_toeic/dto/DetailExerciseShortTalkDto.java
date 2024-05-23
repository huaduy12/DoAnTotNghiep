package com.example.client_toeic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailExerciseShortTalkDto {
    private String name;
    private String level;
    private String type;
    private List<QuestionShortTalkDto> data;
}

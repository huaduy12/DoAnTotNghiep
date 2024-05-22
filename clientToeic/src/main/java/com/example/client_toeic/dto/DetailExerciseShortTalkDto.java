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
    private List<QuestionShortTalkDto> questionShortTalkDtos;
}

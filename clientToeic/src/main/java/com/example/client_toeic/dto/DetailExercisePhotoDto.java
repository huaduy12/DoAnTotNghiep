package com.example.client_toeic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailExercisePhotoDto {
    private Integer id;
    private String name;
    private Integer time;
    private List<QuestionPhotoDto> questionPhotoDtos;
}

package com.example.do_an_toeic.dto;

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
    private List<QuestionPhotoDto> questionPhotoDtos;
}

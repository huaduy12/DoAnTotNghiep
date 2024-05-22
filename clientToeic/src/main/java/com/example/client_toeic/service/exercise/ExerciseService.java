package com.example.client_toeic.service.exercise;

import com.example.client_toeic.dto.QuestionPhotoApi;
import com.example.client_toeic.dto.SimpleNameDto;
import com.example.client_toeic.entity.Exercise;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ExerciseService {
    Page<Exercise> responseListExercise(String typeSkill, String level, Integer page);
    QuestionPhotoApi getDetailPhoto(Integer id);
}

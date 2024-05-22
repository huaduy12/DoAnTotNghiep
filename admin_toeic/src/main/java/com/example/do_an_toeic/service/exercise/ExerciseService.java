package com.example.do_an_toeic.service.exercise;

import com.example.do_an_toeic.dto.*;
import com.example.do_an_toeic.entity.Exercise;

import java.util.List;

public interface ExerciseService {

    List<Exercise> findAll();
    void addExercise(QuestionExerciseDto questionExerciseDto);
    void updateExercise(QuestionExerciseDto questionExerciseDto);

    void deleteExercise(Exercise exercise);

    DetailExercisePhotoDto detailQuestionPhoto(Exercise exercise);

    DetailExerciseShortTalkDto detailQuestionShortTalk(Exercise exercise);

    QuestionExerciseShowUpdateDto responseDetailQuestionExercise(Integer id);
}

package com.example.client_toeic.service.exercise;

import com.example.client_toeic.dto.*;
import com.example.client_toeic.entity.Exercise;
import com.example.client_toeic.repository.ExerciseQuestionRepository;
import com.example.client_toeic.repository.ExerciseRepository;
import com.example.client_toeic.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ExerciseServiceImpl implements ExerciseService{

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private ExerciseQuestionRepository exerciseQuestionRepository;

    @Autowired
    private QuestionService questionService;

    @Override
    public Page<Exercise> responseListExercise(String typeSkill, String level, Integer page) {
        Pageable pageable =  PageRequest.of(page-1,1);
        return exerciseRepository.findAllByTypeSkillAndLevel(typeSkill,level,pageable);
    }

    @Override
    public QuestionPhotoApi getDetailPhoto(Integer id) {
        List<Integer> questionId = exerciseQuestionRepository.findAllByExerciseIdStr(id);
        QuestionPhotoApi questionPhotoApi = new QuestionPhotoApi();
        List<QuestionPhoto> questionPhotos = new ArrayList<>();
        for (Integer idQuestion: questionId) {
            QuestionPhoto questionPhoto = questionService.findQuestionPhoto(idQuestion);
            questionPhotos.add(questionPhoto);
        }
        questionPhotoApi.setQuestions(questionPhotos);
        return questionPhotoApi;
    }

    @Override
    public DetailExerciseShortTalkDto detailQuestionShortTalk(Integer id) {
        Exercise exercise = exerciseRepository.findById(id).get();
        DetailExerciseShortTalkDto detailExerciseShortTalkDto = new DetailExerciseShortTalkDto();
        detailExerciseShortTalkDto.setName(exercise.getName());
        detailExerciseShortTalkDto.setType(exercise.getTypeSkill());
        detailExerciseShortTalkDto.setLevel(exercise.getLevel());

        List<QuestionShortTalkDto> questionShortTalkDto = new ArrayList<>();

        List<Integer> questionIds = exerciseQuestionRepository.findAllByExerciseIdStr(exercise.getId());
        for (Integer idQuestion:questionIds) {
            QuestionShortTalkDto questionPhotoDto = questionService.detailQuestionShortTalk(idQuestion);
            questionShortTalkDto.add(questionPhotoDto);
        }
        detailExerciseShortTalkDto.setData(questionShortTalkDto);
        return detailExerciseShortTalkDto;
    }


}

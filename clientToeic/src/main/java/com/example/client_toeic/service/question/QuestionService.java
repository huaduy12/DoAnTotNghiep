package com.example.client_toeic.service.question;


import com.example.client_toeic.dto.QuestionExerciseDto;
import com.example.client_toeic.dto.QuestionPhoto;
import com.example.client_toeic.dto.QuestionPhotoDto;
import com.example.client_toeic.dto.QuestionShortTalkDto;
import com.example.client_toeic.entity.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionService {



    List<Question> finAll();

    QuestionPhoto findQuestionPhoto(Integer id);

    QuestionShortTalkDto findQuestionShortTalk(Integer id);
    QuestionShortTalkDto detailQuestionShortTalk(Integer id);
    QuestionPhotoDto detailQuestionPhoto(Integer id);

    List<Question> responseQuestionExercise(QuestionExerciseDto questionExerciseDto, Integer typeQuestion);

}

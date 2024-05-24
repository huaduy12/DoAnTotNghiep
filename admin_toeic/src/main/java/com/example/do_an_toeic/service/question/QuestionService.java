package com.example.do_an_toeic.service.question;


import com.example.do_an_toeic.dto.*;
import com.example.do_an_toeic.dto.exam.ExamSkillQuestionDto;
import com.example.do_an_toeic.entity.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionService {

    Question saveQuestionPhoto(QuestionPhotoDto questionPhotoDto) throws IOException;
    Question saveQuestionQuestionResponse(QuestionPhotoDto questionPhotoDto) throws IOException;
    Question saveQuestionShortConversation(QuestionPhotoDto questionPhotoDto) throws IOException;
    Question saveQuestionIncomplete(QuestionPhotoDto questionPhotoDto) throws IOException;
    Question saveQuestionShortTalk(QuestionShortTalkDto questionPhotoDto, String type) throws IOException;
    Question saveQuestionTextCompletion(QuestionShortTalkDto questionPhotoDto, String type) throws IOException;

    Question updateQuestionPhoto(QuestionPhotoDto questionPhotoDto) throws IOException;
    Question updateQuestionResponse(QuestionPhotoDto questionPhotoDto) throws IOException;
    Question updateShortTalk(QuestionShortTalkDto questionPhotoDto) throws IOException;
    Question updateIncomplete(QuestionPhotoDto questionPhotoDto) throws IOException;
    Question updateTextCompletion(QuestionShortTalkDto questionPhotoDto) throws IOException;

    List<Question> finAll();

    void delete(Question question);

    QuestionPhotoDto findQuestionPhoto(Integer id);

    QuestionShortTalkDto findQuestionShortTalk(Integer id);
    QuestionShortTalkDto detailQuestionShortTalk(Integer id);
    QuestionPhotoDto detailQuestionPhoto(Integer id);

    List<Question> responseQuestionExercise(QuestionExerciseDto questionExerciseDto, Integer typeQuestion);
    List<Question> responseQuestionExam(ExamSkillQuestionDto examSkillQuestionDto, Integer typeQuestion);
    List<Question> responseQuestionExamUpdate(ExamSkillQuestionDto examSkillQuestionDto, Integer typeQuestion);

}

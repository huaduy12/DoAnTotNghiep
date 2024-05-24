package com.example.do_an_toeic.service.exam;

import com.example.do_an_toeic.dto.DetailExercisePhotoDto;
import com.example.do_an_toeic.dto.DetailExerciseShortTalkDto;
import com.example.do_an_toeic.dto.QuestionExerciseDto;
import com.example.do_an_toeic.dto.QuestionExerciseShowUpdateDto;
import com.example.do_an_toeic.dto.exam.ExamImportQuestionDto;
import com.example.do_an_toeic.dto.exam.ExamSkillQuestionDto;
import com.example.do_an_toeic.dto.exam.QuestionExamSkillShowUpdateDto;
import com.example.do_an_toeic.entity.Exam;
import com.example.do_an_toeic.entity.Exercise;

import java.io.IOException;
import java.util.List;

public interface ExamSkillService {
    List<Exam> findAll(String type);

    void addExamSkill(ExamSkillQuestionDto examSkillQuestionDto);

    QuestionExamSkillShowUpdateDto responseDetailQuestionExamSkill(Integer id);
    void updateExamSkill(ExamSkillQuestionDto examSkillQuestionDto);

    DetailExercisePhotoDto detailQuestionPhoto(Exam exam);

    DetailExerciseShortTalkDto detailQuestionShortTalk(Exam exam);

    void deleteExamSkill(Integer id);

    void importExamMini(ExamImportQuestionDto examImportQuestionDto) throws IOException;
}

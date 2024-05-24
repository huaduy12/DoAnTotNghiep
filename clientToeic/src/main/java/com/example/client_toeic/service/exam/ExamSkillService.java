package com.example.client_toeic.service.exam;

import com.example.client_toeic.dto.DetailExercisePhotoDto;
import com.example.client_toeic.dto.DetailExerciseShortTalkDto;
import com.example.client_toeic.dto.QuestionPhotoApi;
import com.example.client_toeic.dto.exam.ExamDto;
import com.example.client_toeic.dto.exam.ExamSkillQuestionDto;
import com.example.client_toeic.dto.exam.QuestionExamSkillShowUpdateDto;
import com.example.client_toeic.dto.exam.ResponseExamMini;
import com.example.client_toeic.entity.Exam;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ExamSkillService {
    List<Exam> findAll(String type);

    Page<Exam> listExamSkill(String typeSkill, Integer page);
    QuestionPhotoApi getDetailPhoto(Integer id);

    DetailExerciseShortTalkDto detailQuestionShortTalk(Integer id);

    Page<Exam> listExamMini(String typeSkill,Integer page);

    ResponseExamMini getDetailApiMini(Integer id);
}

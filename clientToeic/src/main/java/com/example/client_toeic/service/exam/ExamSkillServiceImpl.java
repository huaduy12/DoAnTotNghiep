package com.example.client_toeic.service.exam;

import com.example.client_toeic.dto.*;
import com.example.client_toeic.dto.exam.*;
import com.example.client_toeic.entity.*;
import com.example.client_toeic.enums.TypeExam;
import com.example.client_toeic.enums.TypeQuestion;
import com.example.client_toeic.repository.*;
import com.example.client_toeic.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class ExamSkillServiceImpl implements ExamSkillService {
    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private ExerciseQuestionRepository exerciseQuestionRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private ExamQuestionRepository examQuestionRepository;


    @Override
    public List<Exam> findAll(String type) {
        return examRepository.findAllByTypeExam(type);
    }

    @Override
    public Page<Exam> listExamSkill(String typeSkill, Integer page) {
        Pageable pageable =  PageRequest.of(page-1,9);
         return examRepository.findAllByTypeExamSkill(TypeExam.skill.getValue(),typeSkill,pageable);
    }

    @Override
    public QuestionPhotoApi getDetailPhoto(Integer id) {
        List<Integer> questionId = examQuestionRepository.findAllByExamIdStr(id);
        QuestionPhotoApi questionPhotoApi = new QuestionPhotoApi();
        Exam exam = examRepository.findById(id).get();
        questionPhotoApi.setTime(exam.getTimeHomework());
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
        Exam exam = examRepository.findById(id).get();
        DetailExerciseShortTalkDto detailExerciseShortTalkDto = new DetailExerciseShortTalkDto();
        detailExerciseShortTalkDto.setName(exam.getName());
        detailExerciseShortTalkDto.setType(exam.getTypeSkill());
        detailExerciseShortTalkDto.setLevel(exam.getLevel());
        detailExerciseShortTalkDto.setTime(exam.getTimeHomework());

        List<QuestionShortTalkDto> questionShortTalkDto = new ArrayList<>();

        List<Integer> questionIds = examQuestionRepository.findAllByExamIdStr(exam.getId());
        for (Integer idQuestion:questionIds) {
            QuestionShortTalkDto questionPhotoDto = questionService.detailQuestionShortTalk(idQuestion);
            questionShortTalkDto.add(questionPhotoDto);
        }
        detailExerciseShortTalkDto.setData(questionShortTalkDto);
        return detailExerciseShortTalkDto;
    }

    @Override
    public Page<Exam> listExamMini(String typeSkill, Integer page) {
        Pageable pageable =  PageRequest.of(page-1,9);
        Page<Exam> examPage = examRepository.findAllByTypeExamMini(TypeExam.mini.getValue(), pageable);

//        List<ExamDto> examDtos = new ArrayList<>();
//        for (Exam exam:e) {
//
//        }
        return examPage;
    }

    @Override
    public ResponseExamMini getDetailApiMini(Integer id) {
        ResponseExamMini responseExamMini = new ResponseExamMini();
        Exam exam = examRepository.findById(id).get();
        responseExamMini.setTime(exam.getTimeHomework());

        List<DetailQuestionPhotoMini> detailQuestionPhotoMinis = new ArrayList<>();
        // từng part 1
        // part 1
        List<ExamQuestion> examQuestions = examQuestionRepository.findAllByExamId(id);
        List<Question> questions = questionRepository.findAllById(examQuestions.stream().map(ExamQuestion::getQuestionId).collect(Collectors.toList()));

        // lấy ra câu hỏi có part là photo

        return null;
    }


}

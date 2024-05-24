package com.example.do_an_toeic.service.exam;

import com.example.do_an_toeic.dto.*;
import com.example.do_an_toeic.dto.exam.ExamImportQuestionDto;
import com.example.do_an_toeic.dto.exam.ExamSkillQuestionDto;
import com.example.do_an_toeic.dto.exam.QuestionExamSkillShowUpdateDto;
import com.example.do_an_toeic.entity.*;
import com.example.do_an_toeic.enums.TypeExam;
import com.example.do_an_toeic.enums.TypeExercise;
import com.example.do_an_toeic.enums.TypeQuestion;
import com.example.do_an_toeic.repository.*;
import com.example.do_an_toeic.service.import_file.ImportQuestionService;
import com.example.do_an_toeic.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ExamSkillServiceImpl implements ExamSkillService{
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

    @Autowired
    private ImportQuestionService importQuestionService;


    @Override
    public List<Exam> findAll(String type) {
        return examRepository.findAllByTypeExam(type);
    }

    @Override
    public void addExamSkill(ExamSkillQuestionDto examSkillQuestionDto) {
        Exam exerciseSkill = new Exam();
        exerciseSkill.setTypeExam(TypeExam.skill.getValue());
        exerciseSkill.setTypeSkill(examSkillQuestionDto.getSkill());
        exerciseSkill.setName(examSkillQuestionDto.getName().trim());
        exerciseSkill.setTimeHomework(Integer.parseInt(examSkillQuestionDto.getTime()));
        exerciseSkill.setLevel(examSkillQuestionDto.getLevel());
        Exam exam = examRepository.save(exerciseSkill);
        saveExamQuestion(examSkillQuestionDto,exam.getId());
    }

    @Override
    public QuestionExamSkillShowUpdateDto responseDetailQuestionExamSkill(Integer id) {
        Exam exam = examRepository.findById(id).orElseThrow(() -> new RuntimeException("Không có bài phù hợp"));
        QuestionExamSkillShowUpdateDto questionExamSkillShowUpdateDto =new QuestionExamSkillShowUpdateDto();
        questionExamSkillShowUpdateDto.setId(exam.getId());
        questionExamSkillShowUpdateDto.setName(exam.getName());
        questionExamSkillShowUpdateDto.setLevel(exam.getLevel());
        questionExamSkillShowUpdateDto.setSkill(exam.getTypeSkill());
        questionExamSkillShowUpdateDto.setTime(String.valueOf(exam.getTimeHomework()));

        List<Integer> questionIds = examQuestionRepository.findAllByExamIdStr(id);
        List<Question> questions = questionRepository.findAllById(questionIds);
        questionExamSkillShowUpdateDto.setQuestionsUsing(questions);

        List<Question> questions1 = questionRepository.findAllByIdNot(exam.getLevel(),exam.getTypeSkill(), TypeQuestion.TYPE_EXAM.getValue(),questionIds);
        questionExamSkillShowUpdateDto.setQuestions(questions1);

        return questionExamSkillShowUpdateDto;
    }

    @Override
    public void updateExamSkill(ExamSkillQuestionDto examSkillQuestionDto) {
        Exam exam = examRepository.findById(examSkillQuestionDto.getId()).orElseThrow(()-> new RuntimeException(" Không tìm thấy bài phù hợp"));

        exam.setLevel(examSkillQuestionDto.getLevel());
        exam.setTypeSkill(examSkillQuestionDto.getSkill());
        exam.setName(examSkillQuestionDto.getName().trim());
        exam.setTimeHomework(Integer.parseInt(examSkillQuestionDto.getTime()));
        examRepository.save(exam);
        List<ExamQuestion> examQuestions = examQuestionRepository.findAllByExamId(exam.getId());

        examQuestionRepository.deleteAll(examQuestions);
        saveExamQuestion(examSkillQuestionDto,exam.getId());
    }


    public void saveExamQuestion(ExamSkillQuestionDto examSkillQuestionDto, Integer idExam){
        List<ExamQuestion> examQuestions = new ArrayList<>();
        for (QuestionApi questionApi: examSkillQuestionDto.getQuestions()) {
            ExamQuestion examQuestion = new ExamQuestion();
            examQuestion.setExamId(idExam);
            examQuestion.setQuestionId(questionApi.getId());
            examQuestions.add(examQuestion);
        }
        examQuestionRepository.saveAll(examQuestions);
    }


    @Override
    public DetailExercisePhotoDto detailQuestionPhoto(Exam exam) {
        DetailExercisePhotoDto detailExercisePhotoDto = new DetailExercisePhotoDto();
        detailExercisePhotoDto.setName(exam.getName());
        detailExercisePhotoDto.setTime(exam.getTimeHomework());
        List<QuestionPhotoDto> questionPhotoDtos = new ArrayList<>();

        List<Integer> questionIds = examQuestionRepository.findAllByExamIdStr(exam.getId());
        for (Integer idQuestion:questionIds) {
            QuestionPhotoDto questionPhotoDto = questionService.detailQuestionPhoto(idQuestion);
            questionPhotoDtos.add(questionPhotoDto);
        }
        detailExercisePhotoDto.setQuestionPhotoDtos(questionPhotoDtos);
        return detailExercisePhotoDto;
    }

    @Override
    public DetailExerciseShortTalkDto detailQuestionShortTalk(Exam exam) {
        DetailExerciseShortTalkDto detailExerciseShortTalkDto = new DetailExerciseShortTalkDto();
        detailExerciseShortTalkDto.setName(exam.getName());
        detailExerciseShortTalkDto.setTime(exam.getTimeHomework());
        List<QuestionShortTalkDto> questionShortTalkDto = new ArrayList<>();

        List<Integer> questionIds = examQuestionRepository.findAllByExamIdStr(exam.getId());
        for (Integer idQuestion:questionIds) {
            QuestionShortTalkDto questionPhotoDto = questionService.detailQuestionShortTalk(idQuestion);
            questionShortTalkDto.add(questionPhotoDto);
        }
        detailExerciseShortTalkDto.setQuestionShortTalkDtos(questionShortTalkDto);
        return detailExerciseShortTalkDto;
    }

    @Override
    public void deleteExamSkill(Integer id) {

    }

    @Override
    public void importExamMini(ExamImportQuestionDto examImportQuestionDto) throws IOException {
        Exam exam = new Exam();
        exam.setName(examImportQuestionDto.getName());
        exam.setTimeHomework(examImportQuestionDto.getTime());

        if(examImportQuestionDto.getPrice() == null){
            exam.setPrice(0d);
        }else {
            exam.setPrice(examImportQuestionDto.getPrice());
        }
        exam.setTypeExam(TypeExam.mini.getValue());
        exam.setTitle(examImportQuestionDto.getTitle());
        exam.setLinkImage(examImportQuestionDto.getLinkImage());
        Exam examSave =examRepository.save(exam);
        Integer result1 = importQuestionService.importFileQuestionForExam(examImportQuestionDto.getMultipartFile(),examSave.getId());
    }

}

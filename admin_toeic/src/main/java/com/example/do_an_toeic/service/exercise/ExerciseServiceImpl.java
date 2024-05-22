package com.example.do_an_toeic.service.exercise;

import com.example.do_an_toeic.dto.*;
import com.example.do_an_toeic.entity.Exercise;
import com.example.do_an_toeic.entity.ExerciseQuestion;
import com.example.do_an_toeic.entity.Question;
import com.example.do_an_toeic.enums.TypeExercise;
import com.example.do_an_toeic.enums.TypeQuestion;
import com.example.do_an_toeic.repository.ExamQuestionRepository;
import com.example.do_an_toeic.repository.ExerciseQuestionRepository;
import com.example.do_an_toeic.repository.ExerciseRepository;
import com.example.do_an_toeic.repository.QuestionRepository;
import com.example.do_an_toeic.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = {Exception.class})
public class ExerciseServiceImpl implements ExerciseService{

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private ExerciseQuestionRepository exerciseQuestionRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionService questionService;

    @Override
    public List<Exercise> findAll() {
        return exerciseRepository.findAllByTypeExercise(TypeExercise.ONTAP.getValue());
    }

    @Override
    public void addExercise(QuestionExerciseDto questionExerciseDto) {
        Exercise exercise = new Exercise();
        exercise.setTypeExercise(TypeExercise.ONTAP.getValue());
        exercise.setLevel(questionExerciseDto.getLevel());
        exercise.setTypeSkill(questionExerciseDto.getSkill());
        exercise.setName(questionExerciseDto.getName().trim());
        Exercise exerciseSave = exerciseRepository.save(exercise);
        saveExerciseQuestion(questionExerciseDto,exerciseSave.getId());
    }

    @Override
    public void updateExercise(QuestionExerciseDto questionExerciseDto) {
        Exercise exercise = exerciseRepository.findById(questionExerciseDto.getId()).orElseThrow(()-> new RuntimeException(" Không tìm thấy bài ôn tập phù hợp"));
        exercise.setTypeExercise(TypeExercise.ONTAP.getValue());
        exercise.setLevel(questionExerciseDto.getLevel());
        exercise.setTypeSkill(questionExerciseDto.getSkill());
        exercise.setName(questionExerciseDto.getName().trim());
        Exercise exerciseSave = exerciseRepository.save(exercise);
        List<ExerciseQuestion> exerciseQuestions = exerciseQuestionRepository.findAllByExerciseId(exerciseSave.getId());
        exerciseQuestionRepository.deleteAll(exerciseQuestions);
        saveExerciseQuestion(questionExerciseDto,exerciseSave.getId());
    }

    @Override
    public void deleteExercise(Exercise exercise) {

        exerciseRepository.delete(exercise);
        List<ExerciseQuestion> exerciseQuestions = exerciseQuestionRepository.findAllByExerciseId(exercise.getId());
        exerciseQuestionRepository.deleteAll(exerciseQuestions);
    }

    /**
     * xem chi tiết những bài ôn tập có 1 câu hỏi
     * @param exercise
     * @return
     */
    @Override
    public DetailExercisePhotoDto detailQuestionPhoto(Exercise exercise) {
        DetailExercisePhotoDto detailExercisePhotoDto = new DetailExercisePhotoDto();
        detailExercisePhotoDto.setName(exercise.getName());
        List<QuestionPhotoDto> questionPhotoDtos = new ArrayList<>();

        List<Integer> questionIds = exerciseQuestionRepository.findAllByExerciseIdStr(exercise.getId());
        for (Integer idQuestion:questionIds) {
            QuestionPhotoDto questionPhotoDto = questionService.detailQuestionPhoto(idQuestion);
            questionPhotoDtos.add(questionPhotoDto);
        }
        detailExercisePhotoDto.setQuestionPhotoDtos(questionPhotoDtos);
        return detailExercisePhotoDto;
    }

    @Override
    public DetailExerciseShortTalkDto detailQuestionShortTalk(Exercise exercise) {
        DetailExerciseShortTalkDto detailExerciseShortTalkDto = new DetailExerciseShortTalkDto();
        detailExerciseShortTalkDto.setName(exercise.getName());
        List<QuestionShortTalkDto> questionShortTalkDto = new ArrayList<>();

        List<Integer> questionIds = exerciseQuestionRepository.findAllByExerciseIdStr(exercise.getId());
        for (Integer idQuestion:questionIds) {
            QuestionShortTalkDto questionPhotoDto = questionService.detailQuestionShortTalk(idQuestion);
            questionShortTalkDto.add(questionPhotoDto);
        }
        detailExerciseShortTalkDto.setQuestionShortTalkDtos(questionShortTalkDto);
        return detailExerciseShortTalkDto;
    }


    @Override
    public QuestionExerciseShowUpdateDto responseDetailQuestionExercise(Integer id) {
        Exercise exercise = exerciseRepository.findById(id).orElseThrow(() -> new RuntimeException("Không có bài ôn tập phù hợp"));
        QuestionExerciseShowUpdateDto questionExerciseShowUpdateDto =new QuestionExerciseShowUpdateDto();
        questionExerciseShowUpdateDto.setId(exercise.getId());
        questionExerciseShowUpdateDto.setName(exercise.getName());
        questionExerciseShowUpdateDto.setLevel(exercise.getLevel());
        questionExerciseShowUpdateDto.setSkill(exercise.getTypeSkill());

        List<Integer> questionIds = exerciseQuestionRepository.findAllByExerciseIdStr(id);
        List<Question> questions = questionRepository.findAllById(questionIds);
        questionExerciseShowUpdateDto.setQuestionsUsing(questions);

        List<Question> questions1 = questionRepository.findAllByIdNot(exercise.getLevel(),exercise.getTypeSkill(), TypeQuestion.TYPE_EXERCISE.getValue(),questionIds);
        questionExerciseShowUpdateDto.setQuestions(questions1);

        return questionExerciseShowUpdateDto;
    }


    public void saveExerciseQuestion(QuestionExerciseDto questionExerciseDto, Integer idExercise){
        List<ExerciseQuestion> exerciseQuestions = new ArrayList<>();
        for (QuestionApi questionApi: questionExerciseDto.getQuestions()) {
            ExerciseQuestion exerciseQue = new ExerciseQuestion();
            exerciseQue.setExerciseId(idExercise);
            exerciseQue.setQuestionId(questionApi.getId());
            exerciseQuestions.add(exerciseQue);
        }
        exerciseQuestionRepository.saveAll(exerciseQuestions);
    }
}

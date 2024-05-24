package com.example.do_an_toeic.controller.manage_exam;

import com.example.do_an_toeic.dto.QuestionExerciseDto;
import com.example.do_an_toeic.dto.QuestionExerciseShowUpdateDto;
import com.example.do_an_toeic.dto.exam.ExamSkillQuestionDto;
import com.example.do_an_toeic.dto.exam.QuestionExamSkillShowUpdateDto;
import com.example.do_an_toeic.service.exam.ExamSkillService;
import com.example.do_an_toeic.service.exercise.ExerciseService;
import com.example.do_an_toeic.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/skill")
public class ApiManageExamSkill {
    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private ExamSkillService examSkillService;

    @PostMapping("/add")
    ResponseEntity<String> addExercise(@RequestBody ExamSkillQuestionDto examSkillQuestionDto){
        validateAdd(examSkillQuestionDto);
        examSkillService.addExamSkill(examSkillQuestionDto);
        return ResponseEntity.ok("Thành công");
    }

    @GetMapping("/question-skill/{id}")
    public ResponseEntity<QuestionExamSkillShowUpdateDto> showDetailUpdate(@PathVariable("id") Integer id){
        return ResponseEntity.ok(examSkillService.responseDetailQuestionExamSkill(id));
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateExcersice(@RequestBody ExamSkillQuestionDto examSkillQuestionDto){
        validateAdd(examSkillQuestionDto);
        examSkillService.updateExamSkill(examSkillQuestionDto);
        return ResponseEntity.ok("Thành công");
    }

    public void validateAdd(ExamSkillQuestionDto examSkillQuestionDto){
        if(CommonUtils.isEmptyOrNull(examSkillQuestionDto.getName())){
            throw new RuntimeException("Tên bài kỹ năng không được để trống");
        }

        if(CommonUtils.isEmptyOrNull(examSkillQuestionDto.getTime())){
            throw new RuntimeException("Thời gian làm bài không được để trống");
        }
        try{
            Integer time = Integer.parseInt(examSkillQuestionDto.getTime());
        }catch (Exception e){
            throw new RuntimeException("Vui lòng nhập thời gian làm bài làm số phút");
        }

        if(CommonUtils.isEmptyOrNull(examSkillQuestionDto.getSkill())){
            throw new RuntimeException("Kỹ năng toeic không được để trống khi thêm bài");
        }
        if(CommonUtils.isListEmptyOrNull(examSkillQuestionDto.getQuestions())){
            throw new RuntimeException("Vui lòng chọn ít nhất 1 câu hỏi");
        }

    }
}

package com.example.do_an_toeic.controller.manage_excercise;

import com.example.do_an_toeic.dto.QuestionExerciseDto;
import com.example.do_an_toeic.dto.QuestionExerciseShowUpdateDto;
import com.example.do_an_toeic.service.exercise.ExerciseService;
import com.example.do_an_toeic.service.question.QuestionService;
import com.example.do_an_toeic.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exercise")
public class ApiManageExercise {

    @Autowired
    private ExerciseService exerciseService;

    @PostMapping("/add")
    ResponseEntity<String> addExercise(@RequestBody QuestionExerciseDto questionExerciseDto){
        validateAdd(questionExerciseDto);
        exerciseService.addExercise(questionExerciseDto);
        return ResponseEntity.ok("Thành công");
    }

    @GetMapping("/question-exercise/{id}")
    public ResponseEntity<QuestionExerciseShowUpdateDto> showDetailUpdate(@PathVariable("id") Integer id){
        return ResponseEntity.ok(exerciseService.responseDetailQuestionExercise(id));
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateExcersice(@RequestBody QuestionExerciseDto questionExerciseDto){
        validateAdd(questionExerciseDto);
        exerciseService.updateExercise(questionExerciseDto);
        return ResponseEntity.ok("Thành công");
    }

    public void validateAdd(QuestionExerciseDto questionExerciseDto){
        if(CommonUtils.isEmptyOrNull(questionExerciseDto.getName())){
            throw new RuntimeException("Tên bài ôn tập không được để trống");
        }
        if(CommonUtils.isEmptyOrNull(questionExerciseDto.getLevel())){
            throw new RuntimeException("Level toeic không được để trống khi thêm bài ôn tập");
        }
        if(CommonUtils.isEmptyOrNull(questionExerciseDto.getSkill())){
            throw new RuntimeException("Kỹ năng toeic không được để trống khi thêm bài ôn tập");
        }
        if(CommonUtils.isListEmptyOrNull(questionExerciseDto.getQuestions())){
            throw new RuntimeException("Vui lòng chọn ít nhất 1 câu hỏi");
        }

    }
}

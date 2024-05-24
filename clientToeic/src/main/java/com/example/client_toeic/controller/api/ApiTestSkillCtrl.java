package com.example.client_toeic.controller.api;

import com.example.client_toeic.dto.DetailExerciseShortTalkDto;
import com.example.client_toeic.dto.QuestionPhotoApi;
import com.example.client_toeic.service.exam.ExamSkillService;
import com.example.client_toeic.service.exercise.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/skill")
public class ApiTestSkillCtrl {


    @Autowired
    private ExamSkillService examSkillService;

    @GetMapping("/{id}")
    public ResponseEntity<QuestionPhotoApi> getReviewPhoto(@PathVariable("id") Integer id){
        return ResponseEntity.ok(examSkillService.getDetailPhoto(id));
    }

    @GetMapping("/talk/{id}")
    public ResponseEntity<DetailExerciseShortTalkDto> getReviewShortTalk(@PathVariable("id") Integer id){

        return ResponseEntity.ok(examSkillService.detailQuestionShortTalk(id));
    }
}

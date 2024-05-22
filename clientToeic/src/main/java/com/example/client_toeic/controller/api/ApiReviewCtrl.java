package com.example.client_toeic.controller.api;

import com.example.client_toeic.dto.QuestionPhotoApi;
import com.example.client_toeic.service.exercise.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/review")
public class ApiReviewCtrl {

    @Autowired
    private ExerciseService exerciseService;

    @GetMapping("/{id}")
    public ResponseEntity<QuestionPhotoApi> getReviewPhoto(@PathVariable("id") Integer id){
        return ResponseEntity.ok(exerciseService.getDetailPhoto(id));
    }
}

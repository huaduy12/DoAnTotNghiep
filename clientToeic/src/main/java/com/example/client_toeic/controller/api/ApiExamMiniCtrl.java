package com.example.client_toeic.controller.api;

import com.example.client_toeic.dto.exam.ResponseExamMini;
import com.example.client_toeic.service.exam.ExamSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class ApiExamMiniCtrl {

    @Autowired
    private ExamSkillService examSkillService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseExamMini> detail(@PathVariable("id") Integer id){

        return null;
    }
}

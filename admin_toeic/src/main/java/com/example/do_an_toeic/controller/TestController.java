package com.example.do_an_toeic.controller;

import com.example.do_an_toeic.service.firebase.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ImageUploadService imageUploadService;

    @PostMapping("/test")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile multipartFile){
        System.out.println("Day: "+multipartFile);
        String link = imageUploadService.upload(multipartFile);
        return ResponseEntity.ok(link);
    }
}

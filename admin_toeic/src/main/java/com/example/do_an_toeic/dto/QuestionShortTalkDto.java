package com.example.do_an_toeic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionShortTalkDto {
    private String id;
    private String name;
    private String level;
    private String type;
    private String description;
    private FileBase64Dto image;
    private FileBase64Dto audio;
    List<ChildrenQuestionDto> questions;
}

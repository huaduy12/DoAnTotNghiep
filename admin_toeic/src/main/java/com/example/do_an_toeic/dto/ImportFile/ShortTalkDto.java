package com.example.do_an_toeic.dto.ImportFile;

import com.example.do_an_toeic.dto.ChildrenQuestionDto;
import com.example.do_an_toeic.dto.FileBase64Dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShortTalkDto {
    private String id;
    private String name;
    private String level;
    private Integer typeQuestion;
    private String description;
    private String image;
    private String audio;
    List<ChildrenShortTalkDto> questions;
}

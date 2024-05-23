package com.example.do_an_toeic.dto.ImportFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChildrenShortTalkDto {
    private String id;
    private String name;
    private String level;
    private String type;
    private String description;
    private Integer answerCorrect;
    private List<String> answers;
    private String answer;
}

package com.example.do_an_toeic.dto.ImportFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoDto {
    private String linkAudio;
    private String linkImage;
    private String name;
    private String answer;
    private String description;
    private List<String> answers;
    private Integer indexCorrect;
    private Integer typeQuestion;
    private String level;
}

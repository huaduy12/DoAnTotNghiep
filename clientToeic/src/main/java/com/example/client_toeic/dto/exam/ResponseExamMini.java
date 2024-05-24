package com.example.client_toeic.dto.exam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseExamMini {
    private Integer time;
    private List<DetailQuestionPhotoMini> data;
}

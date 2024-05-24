package com.example.client_toeic.entity;

import com.example.client_toeic.utils.EntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "exam_question")
public class ExamQuestion extends EntityBase {
    private Integer examId;
    private Integer questionId;
}

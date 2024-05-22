package com.example.client_toeic.entity;

import com.example.client_toeic.utils.EntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "answer")
public class Answer extends EntityBase {

    @Column(name = "content")
    private String content;

    @Column(name = "question_id")
    private Integer questionId;

    @Column(name = "resource_id")
    private Integer resourceId;
}

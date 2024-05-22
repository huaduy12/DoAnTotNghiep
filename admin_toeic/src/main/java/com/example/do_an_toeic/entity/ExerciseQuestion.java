package com.example.do_an_toeic.entity;

import com.example.do_an_toeic.utils.EntityBase;
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
@Table(name = "exercise_question")
public class ExerciseQuestion extends EntityBase {

    @Column(name = "exercise_id")
    private Integer exerciseId;

    @Column(name = "question_id")
    private Integer questionId;

}

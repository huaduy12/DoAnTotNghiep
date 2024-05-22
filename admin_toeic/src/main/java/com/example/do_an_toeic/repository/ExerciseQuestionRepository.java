package com.example.do_an_toeic.repository;

import com.example.do_an_toeic.entity.ExerciseQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseQuestionRepository extends JpaRepository<ExerciseQuestion,Integer> {
    List<ExerciseQuestion> findAllByQuestionId(Integer questionId);

    @Query("select t.questionId from ExerciseQuestion t where t.exerciseId = :exerciseId")
    List<Integer> findAllByExerciseIdStr(Integer exerciseId);

    List<ExerciseQuestion> findAllByExerciseId(Integer id);
}

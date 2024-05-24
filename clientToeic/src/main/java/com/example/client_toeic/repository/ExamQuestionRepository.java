package com.example.client_toeic.repository;

import com.example.client_toeic.entity.ExamQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamQuestionRepository extends JpaRepository<ExamQuestion,Integer> {
    List<ExamQuestion> findAllByQuestionId(Integer questionId);

    @Query("select t.questionId from ExamQuestion t where t.examId = :examId")
    List<Integer> findAllByExamIdStr(Integer examId);

    List<ExamQuestion> findAllByExamId(Integer examId);



}

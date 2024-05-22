package com.example.do_an_toeic.repository;

import com.example.do_an_toeic.entity.ExamQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamQuestionRepository extends JpaRepository<ExamQuestion,Integer> {
    List<ExamQuestion> findAllByQuestionId(Integer questionId);

}

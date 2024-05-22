package com.example.do_an_toeic.repository;

import com.example.do_an_toeic.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Integer> {
    List<Answer> findAllByQuestionId(Integer questionId);

    @Query("select a.content from Answer a where a.questionId = :questionId")
    List<String> findAllByQuestionIdContent(Integer questionId);

    @Query("select t from Answer t where t.questionId in :questionIds")
    List<Answer> findAllByQuestionIdIn(List<Integer> questionIds);
}

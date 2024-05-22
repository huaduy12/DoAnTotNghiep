package com.example.client_toeic.repository;

import com.example.client_toeic.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {

    @Query("select q from Question q order by q.createdDate desc")
    List<Question> findAll();

    List<Question> findAllByParentQuestion(Integer id);

    @Query("select q from Question q where q.level = :level and q.typeSkill = :skill and q.typeQuestion = :typeQuestion and q.parentQuestion is null order by q.createdDate desc ")
    List<Question> findAllByLevelAndSkill(String level,String skill, Integer typeQuestion);

    @Query("select q from Question q where q.level = :level and q.typeSkill = :typeSkill and q.typeQuestion = :typeQuestion and q.id not in :ids and q.parentQuestion is null order by q.createdDate desc ")
    List<Question> findAllByIdNot(String level,String typeSkill,Integer typeQuestion , List<Integer> ids);
}

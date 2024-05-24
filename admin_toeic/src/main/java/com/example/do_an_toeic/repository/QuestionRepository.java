package com.example.do_an_toeic.repository;

import com.example.do_an_toeic.entity.Question;
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

    // lấy ra những câu hỏi trong skill nhưng câu hỏi đó chưa được thêm vào đề thi có phí tránh trường hợp trùng
    @Query("select q from Question q where q.level = :level and q.typeSkill = :skill and q.typeQuestion = :typeQuestion and q.parentQuestion is null and q.isPrice = false order by q.createdDate desc ")
    List<Question> findAllByLevelAndSkillExam(String level,String skill, Integer typeQuestion);

    @Query("select q from Question q where q.level = :level and q.typeSkill = :typeSkill and q.typeQuestion = :typeQuestion and q.id not in :ids and q.parentQuestion is null order by q.createdDate desc ")
    List<Question> findAllByIdNot(String level,String typeSkill,Integer typeQuestion , List<Integer> ids);

    @Query("select q from Question q where q.level = :level and q.typeSkill = :skill and q.id not in :questionIds and q.typeQuestion = :typeQuestion and q.parentQuestion is null and q.isPrice = false order by q.createdDate desc ")
    List<Question> findAllByLevelAndSkillExamUpdate(String level,String skill,List<Integer> questionIds, Integer typeQuestion);
}

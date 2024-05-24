package com.example.do_an_toeic.repository;

import com.example.do_an_toeic.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam,Integer> {

    @Query("select e from Exam e where e.typeExam = :typeExam")
    List<Exam> findAllByTypeExam(String typeExam);  // skill, mini,full


}

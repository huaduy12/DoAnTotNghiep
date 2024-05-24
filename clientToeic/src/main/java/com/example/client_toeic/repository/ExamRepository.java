package com.example.client_toeic.repository;

import com.example.client_toeic.entity.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam,Integer> {

    @Query("select e from Exam e where e.typeExam = :typeExam")
    List<Exam> findAllByTypeExam(String typeExam);  // skill, mini,full

    @Query("select e from Exam e where e.typeExam = :typeExam")
    Page<Exam> findAllByTypeExamPage(String typeExam, Pageable pageable);  // skill, mini,full

    @Query("select e from Exam e where e.typeExam = :typeExam and e.typeSkill = :typeSkill and e.isActive = true order by e.createdDate desc ")
    Page<Exam> findAllByTypeExamSkill(String typeExam, String typeSkill, Pageable pageable);  // skill, mini,full

    @Query("select e from Exam e where e.typeExam = :typeExam and e.isActive = true order by e.createdDate desc")
    Page<Exam> findAllByTypeExamMini(String typeExam,Pageable pageable);
}

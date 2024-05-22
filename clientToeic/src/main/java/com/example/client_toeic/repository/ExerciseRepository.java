package com.example.client_toeic.repository;


import com.example.client_toeic.entity.Exercise;
import com.example.client_toeic.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise,Integer> {

    @Query("select e from Exercise e where e.typeExercise = :typeExercise order by e.createdDate desc")
    Page<Exercise> findAllByTypeExercise(String typeExercise, Pageable pageable);

    @Query("select n from Exercise n where n.id <> :id and n.typeExercise = :typeExercise order by n.createdDate desc")
    List<Exercise> findByIdOther(Integer id,String typeExercise);


    @Query("select t from Exercise t where t.typeSkill = :typeSkill and t.level = :level order by t.createdDate desc")
    Page<Exercise> findAllByTypeSkillAndLevel(String typeSkill,String level, Pageable pageable);
}

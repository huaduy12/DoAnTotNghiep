package com.example.do_an_toeic.repository;

import com.example.do_an_toeic.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise,Integer> {

    @Query("select e from Exercise e where e.typeExercise = :typeExercise order by e.createdDate desc")
    List<Exercise> findAllByTypeExercise(String typeExercise);

}

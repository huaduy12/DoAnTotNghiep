package com.example.client_toeic.repository;

import com.example.client_toeic.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<Resource,Integer> {

    Resource findByNewsId(Integer id);
    List<Resource> findAllByQuestionId(Integer id);
    Resource findByRelatedDocumentId(Integer id);
    Resource findByExerciseId(Integer id);
    Resource findByUserId(Integer id);
    List<Resource> findByNewsIdIn(List<Integer> ids);
    List<Resource> findByRelatedDocumentIdIn(List<Integer> ids);
    List<Resource> findByExerciseIdIn(List<Integer> ids);

    @Query("select r from Resource r where r.newsId is not null")
    List<Resource> findAllByNewsId();

    @Query("select r from Resource r where r.typeResource = :typeResource and r.fileName is not null and r.isDeleted = false order by r.createdDate desc")
    List<Resource> findAllByTypeResource(String typeResource);
}

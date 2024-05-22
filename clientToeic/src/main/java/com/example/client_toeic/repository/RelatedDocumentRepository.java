package com.example.client_toeic.repository;

import com.example.client_toeic.entity.RelatedDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelatedDocumentRepository extends JpaRepository<RelatedDocument,Integer> {

    @Query("select r from RelatedDocument r order by r.createdDate desc")
    List<RelatedDocument> findAll();

    @Query("select r from RelatedDocument r where r.levelToeic = :type order by r.createdDate desc")
    List<RelatedDocument> findAllByType(String type);

    @Query("select r from RelatedDocument r where r.id <> :id order by r.createdDate desc")
    List<RelatedDocument> findAllByNotId(Integer id);
}

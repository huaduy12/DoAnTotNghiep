package com.example.do_an_toeic.repository;

import com.example.do_an_toeic.entity.RelatedDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelatedDocumentRepository extends JpaRepository<RelatedDocument,Integer> {

    @Query("select r from RelatedDocument r order by r.createdDate desc")
    List<RelatedDocument> findAll();
}

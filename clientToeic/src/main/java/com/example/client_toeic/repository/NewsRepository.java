package com.example.client_toeic.repository;

import com.example.client_toeic.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News,Integer> {

    @Query("select n from News n order by n.createdDate desc")
    Page<News> findAll(Pageable pageable);

    @Query("select n from News n order by n.createdDate desc")
    List<News> findAll();

    List<News> findTop6ByOrderByCreatedDateDesc();

    @Query("select n from News n where n.id <> :id order by n.createdDate desc")
    List<News> findByIdOther(Integer id);
}

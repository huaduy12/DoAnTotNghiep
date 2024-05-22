package com.example.do_an_toeic.repository;

import com.example.do_an_toeic.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News,Integer> {

    @Query("select n from News n order by n.createdDate desc")
    List<News> findAll();
}

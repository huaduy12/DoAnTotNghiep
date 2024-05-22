package com.example.do_an_toeic.service.news;

import com.example.do_an_toeic.dto.NewsDto;
import com.example.do_an_toeic.dto.RelatedDocumentDto;
import com.example.do_an_toeic.entity.News;

import java.util.List;

public interface NewsService {
    List<NewsDto> getAll();

    News save(NewsDto newsDto, String nameImage);

    NewsDto findById(Integer id);

    NewsDto detailUpdate(Integer id);

    NewsDto detailShow(Integer id);

    boolean delete(Integer id);
}

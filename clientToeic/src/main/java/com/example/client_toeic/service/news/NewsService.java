package com.example.client_toeic.service.news;

import com.example.client_toeic.dto.NewsDto;
import com.example.client_toeic.entity.News;
import org.springframework.data.domain.Page;

import java.util.List;

public interface NewsService {

    List<NewsDto> getNews6First();
    Page<NewsDto> getAll(Integer page);

    NewsDto findById(Integer id);

    NewsDto detail(Integer id);

    List<NewsDto> newsOthers(Integer newsId);
}

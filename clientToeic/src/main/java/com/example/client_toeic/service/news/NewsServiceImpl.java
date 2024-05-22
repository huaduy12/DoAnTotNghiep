package com.example.client_toeic.service.news;

import com.example.client_toeic.dto.NewsDto;
import com.example.client_toeic.entity.News;
import com.example.client_toeic.entity.Resource;
import com.example.client_toeic.repository.NewsRepository;
import com.example.client_toeic.repository.ResourceRepository;
import com.example.client_toeic.enums.TypeResource;
import com.example.client_toeic.service.firebase.ImageUploadService;
import com.example.client_toeic.service.resource.ResourceService;
import com.example.client_toeic.utils.CommonUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService{
    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ImageUploadService imageUploadService;


    @Override
    public List<NewsDto> getNews6First() {
        List<NewsDto> newsDtos = modelMapper.map(newsRepository.findTop6ByOrderByCreatedDateDesc(),new TypeToken<List<NewsDto>>(){}.getType());
        List<Integer> newsIds = newsDtos.stream().map(NewsDto::getId).collect(Collectors.toList());
        List<Resource> resources = resourceService.findByNewsIds(newsIds);
        Map<Integer, Resource> mapResources = resources.stream()
                .collect(Collectors.toMap(Resource::getNewsId, Function.identity()));

        for (NewsDto newsDto: newsDtos) {
            Resource resource = mapResources.get(newsDto.getId());
            newsDto.setFileName(resource.getFileName());
            newsDto.setLinkImage(resource.getImage());
        }
        return newsDtos;
    }

    @Override
    public Page<NewsDto> getAll(Integer page) {
        Pageable pageable = null;

        pageable =  PageRequest.of(page-1,12);

        Page<News> news = newsRepository.findAll(pageable);
        if(page.equals(1)){
            List<News> newsFrom4 = news.getContent().stream().skip(6).collect(Collectors.toList());
            return new PageImpl<>(setResourceForNews(newsFrom4),pageable,news.getTotalElements());
        }else{
            return new PageImpl<>(setResourceForNews(news.getContent()),pageable,news.getTotalElements());
        }

    }

    private List<NewsDto> setResourceForNews(List<News> news){
        List<NewsDto> newsDtos = modelMapper.map(news,new TypeToken<List<NewsDto>>(){}.getType());
        List<Integer> newsIds = newsDtos.stream().map(NewsDto::getId).collect(Collectors.toList());
        List<Resource> resources = resourceService.findByNewsIds(newsIds);
        Map<Integer, Resource> mapResources = resources.stream()
                .collect(Collectors.toMap(Resource::getNewsId, Function.identity()));
        for (NewsDto newsDto: newsDtos) {
            Resource resource = mapResources.get(newsDto.getId());
            newsDto.setFileName(resource.getFileName());
            newsDto.setLinkImage(resource.getImage());
        }
        return newsDtos;
    }

    @Override
    public NewsDto findById(Integer id) {
        return modelMapper.map(newsRepository.findById(id).orElseGet(null),NewsDto.class);
    }

    @Override
    public NewsDto detail(Integer id) {
        NewsDto newsDto = findById(id);
        if(newsDto != null){
            Resource resource = resourceRepository.findByNewsId(id);
            if(!CommonUtils.isEmptyOrNull(resource.getFileName())){
                newsDto.setFileName(resource.getFileName());
            }else{
                newsDto.setLinkImage(resource.getImage());
            }
            return newsDto;
        }
        return null;
    }

    @Override
    public List<NewsDto> newsOthers(Integer newsId) {
        List<News> news = newsRepository.findByIdOther(newsId);
        return setResourceForNews(news.stream().limit(20).collect(Collectors.toList()));
    }

}

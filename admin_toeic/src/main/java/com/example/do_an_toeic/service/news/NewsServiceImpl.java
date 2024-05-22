package com.example.do_an_toeic.service.news;

import com.example.do_an_toeic.dto.NewsDto;
import com.example.do_an_toeic.dto.RelatedDocumentDto;
import com.example.do_an_toeic.dto.UserDto;
import com.example.do_an_toeic.entity.News;
import com.example.do_an_toeic.entity.Resource;
import com.example.do_an_toeic.enums.TypeResource;
import com.example.do_an_toeic.repository.NewsRepository;
import com.example.do_an_toeic.repository.ResourceRepository;
import com.example.do_an_toeic.service.firebase.ImageUploadService;
import com.example.do_an_toeic.service.resource.ResourceService;
import com.example.do_an_toeic.utils.CommonUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<NewsDto> getAll() {
        List<News> news = newsRepository.findAll();
        List<NewsDto> newsDtos = modelMapper.map(news,new TypeToken<List<NewsDto>>(){}.getType());
        List<Integer> newsIds = news.stream().map(News::getId).collect(Collectors.toList());
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
    @Transactional
    public News save(NewsDto newsDto, String nameImage) {
        News news = modelMapper.map(newsDto,News.class);
        if(newsDto.getId() != null){

            News newsUpdate = newsRepository.findById(newsDto.getId()).orElseGet(null);
            newsUpdate.setName(newsDto.getName());
            newsUpdate.setTitle(news.getTitle());
            newsUpdate.setDescription(news.getDescription());

            News newUpdateSave = newsRepository.save(newsUpdate);

            Resource resource = resourceRepository.findByNewsId(newsDto.getId());
            if(!CommonUtils.isEmptyOrNull(newsDto.getLinkImage())){
                resource.setImage(newsDto.getLinkImage());
                resource.setFileName(null);
            }else if(CommonUtils.isEmptyOrNull(newsDto.getLinkImage()) &&
                    !CommonUtils.isEmptyOrNull(nameImage) &&
                    !nameImage.equals("No chosen")){
                if(!nameImage.equals(resource.getFileName())){
                    String linkImage = imageUploadService.upload(newsDto.getFileImage());
                    resource.setImage(linkImage);
                    resource.setFileName(newsDto.getFileImage().getOriginalFilename());
                }
            }else if(CommonUtils.isEmptyOrNull(newsDto.getLinkImage()) &&
                    CommonUtils.isEmptyOrNull(newsDto.getFileImage().getOriginalFilename())){
                resource.setImage("");
                resource.setFileName(null);
            }
            resourceRepository.save(resource);
            return newUpdateSave;
        }else{
            news.setNumberAccess(0);
            News newsSave = newsRepository.save(news);
            // lưu ảnh
            Resource resource = new Resource();
            resource.setNewsId(newsSave.getId());
            if (newsDto.getFileImage() != null && !newsDto.getFileImage().isEmpty()) {
                resource.setFileName(newsDto.getFileImage().getOriginalFilename());
            }
            resource.setTypeResource(TypeResource.IMAGE.getValue());

            if(!CommonUtils.isEmptyOrNull(newsDto.getLinkImage())){
                resource.setImage(newsDto.getLinkImage());
            }else {
                String linkImage = imageUploadService.upload(newsDto.getFileImage());
                resource.setImage(linkImage);
            }
            resourceRepository.save(resource);
            return newsSave;
        }

    }

    @Override
    public NewsDto findById(Integer id) {
        return modelMapper.map(newsRepository.findById(id).orElseGet(null),NewsDto.class);
    }

    @Override
    public NewsDto detailUpdate(Integer id) {
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
    public NewsDto detailShow(Integer id) {
        NewsDto newsDto = findById(id);
        if(newsDto != null){
            Resource resource = resourceRepository.findByNewsId(id);
                newsDto.setFileName(resource.getFileName());
                newsDto.setLinkImage(resource.getImage());
            return newsDto;
        }
        return null;
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        News news = newsRepository.findById(id).orElseGet(null);
        if(news == null){
            return false;
        }else {
            Resource resource = resourceService.findByNewsId(id);
            resource.setIsDeleted(true);
            resourceRepository.save(resource);
            newsRepository.delete(news);
            return true;
        }
    }


}

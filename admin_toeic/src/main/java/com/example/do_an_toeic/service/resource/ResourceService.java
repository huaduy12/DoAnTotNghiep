package com.example.do_an_toeic.service.resource;

import com.example.do_an_toeic.dto.FileDto;
import com.example.do_an_toeic.dto.ResourceDto;
import com.example.do_an_toeic.entity.Resource;
import com.example.do_an_toeic.enums.TypeResource;

import java.util.List;

public interface ResourceService {

    List<Resource> finAll();

    Resource findById(Integer id);

    Resource findByNewsId(Integer id);

    List<Resource> findByNewsIds(List<Integer> ids);

    List<Resource> findByRelatedDocumentIds(List<Integer> ids);
    List<Resource> findByExerciseIds(List<Integer> ids);


    List<Resource> findAllNewsId();

    List<ResourceDto> getResourceImage();

    List<ResourceDto> getResourceAudio();

    Resource saveFile(ResourceDto resourceDto, String typeResource);

    Boolean deleteFile(Integer id);
}

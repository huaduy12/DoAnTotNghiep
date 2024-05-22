package com.example.client_toeic.service.self_learning;

import com.example.client_toeic.dto.SelfLearningDto;
import com.example.client_toeic.entity.Exercise;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SelfLearningService {

    Page<SelfLearningDto> getAll(String type, Integer page);
    Exercise save(SelfLearningDto selfLearningDto, String nameImage, String type);

    boolean delete(Integer id);

    Exercise findById(Integer id);

    SelfLearningDto detailShowUpdate(Integer id);

    SelfLearningDto detail(Integer id);

    List<SelfLearningDto> getLearningOther(String type, Integer id);
}

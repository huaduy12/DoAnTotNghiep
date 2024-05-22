package com.example.do_an_toeic.service.self_learning;

import com.example.do_an_toeic.dto.RelatedDocumentDto;
import com.example.do_an_toeic.dto.SelfLearningDto;
import com.example.do_an_toeic.entity.Exercise;
import com.example.do_an_toeic.entity.RelatedDocument;

import java.util.List;

public interface SelfLearningService {

    List<SelfLearningDto> getAll(String type);
    Exercise save(SelfLearningDto selfLearningDto, String nameImage, String type);

    boolean delete(Integer id);

    Exercise findById(Integer id);

    SelfLearningDto detailShowUpdate(Integer id);

    SelfLearningDto detail(Integer id);
}

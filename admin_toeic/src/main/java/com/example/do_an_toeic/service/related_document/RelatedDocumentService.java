package com.example.do_an_toeic.service.related_document;

import com.example.do_an_toeic.dto.NewsDto;
import com.example.do_an_toeic.dto.RelatedDocumentDto;
import com.example.do_an_toeic.entity.News;
import com.example.do_an_toeic.entity.RelatedDocument;

import java.util.List;

public interface RelatedDocumentService {
    List<RelatedDocumentDto> getAll();

    RelatedDocument save(RelatedDocumentDto relatedDocumentDto, String nameImage);

    RelatedDocumentDto findById(Integer id);

    RelatedDocumentDto detailUpdate(Integer id);

    RelatedDocumentDto detailShow(Integer id);

    boolean delete(Integer id);
}

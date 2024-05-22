package com.example.client_toeic.service.related_document;

import com.example.client_toeic.dto.RelatedDocumentDto;
import com.example.client_toeic.entity.RelatedDocument;

import java.util.List;

public interface RelatedDocumentService {
    List<RelatedDocumentDto> getAll();

    RelatedDocument save(RelatedDocumentDto relatedDocumentDto, String nameImage);

    RelatedDocumentDto findById(Integer id);

    RelatedDocumentDto detailUpdate(Integer id);

    RelatedDocumentDto detailShow(Integer id);

    boolean delete(Integer id);

    List<RelatedDocument> findAllByType(String type);

    List<RelatedDocumentDto> findAllByOther(Integer id);
}

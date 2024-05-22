package com.example.do_an_toeic.entity;

import com.example.do_an_toeic.utils.EntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "resource")
public class Resource extends EntityBase {

    @Column(name = "audio")
    private String audio;

    @Column(name = "image")
    private String image;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "type")
    private String typeResource;

    @Column(name = "question_id")
    private Integer questionId;

    @Column(name = "related_document_id")
    private Integer relatedDocumentId;

    @Column(name = "news_id")
    private Integer newsId;

    @Column(name = "exercise_id")
    private Integer exerciseId;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

}

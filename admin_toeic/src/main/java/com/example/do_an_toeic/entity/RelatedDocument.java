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
@Table(name = "related_document")
public class RelatedDocument extends EntityBase {

    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "level_toeic")
    private String levelToeic;

    @Column(name = "number_access")
    private Integer numberAccess;

}

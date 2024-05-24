package com.example.do_an_toeic.entity;

import com.example.do_an_toeic.utils.EntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "question")
public class Question extends EntityBase {
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "type_question")
    private Integer typeQuestion;

    @Column(name = "type_skill")
    private String typeSkill;

    @Column(name = "level")
    private String level;

    @Column(name = "index_correct")
    private Integer indexCorrect;

    @Column(name = "parent_question")
    private Integer parentQuestion;

    @Column(name = "is_price")
    private Boolean isPrice = false;
}

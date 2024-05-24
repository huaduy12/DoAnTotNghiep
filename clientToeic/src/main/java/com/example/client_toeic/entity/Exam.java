package com.example.client_toeic.entity;


import com.example.client_toeic.utils.EntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "exam")
public class Exam extends EntityBase {

    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "level")
    private String level;

    @Column(name = "price")
    private Double price =0d;

    @Column(name = "time_homework")
    private Integer timeHomework;

    @Column(name = "is_active")
    private Boolean isActive =true;

    @Column(name = "type_exam")  // skill, mini, full
    private String typeExam;

    @Column(name = "type_level_toeic")
    private String typeLevelToeic;

    @Column(name = "number_access")
    private Integer numberAccess;

    @Column(name = "type_skill")  // đối với skill test
    private String typeSkill;

    @Column(name = "linkImage")
    private String linkImage;
}

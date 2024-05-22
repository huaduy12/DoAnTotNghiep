package com.example.do_an_toeic.entity;

import com.example.do_an_toeic.utils.EntityBase;
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
@Table(name = "exercise")
public class Exercise extends EntityBase {
    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "level")
    private String level;

    @Column(name = "time_homework")
    private Integer timeHomework;  // thời gian tính theo phút

    @Column(name = "type_exercise")
    private String typeExercise;

    @Column(name = "type_level_toeic")
    private String typeLevelToeic;

    @Column(name = "number_access")
    private Integer numberAccess;

    @Column(name = "type_skill")
    private String typeSkill;
}

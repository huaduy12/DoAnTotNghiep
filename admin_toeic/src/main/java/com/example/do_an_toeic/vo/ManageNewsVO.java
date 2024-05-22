package com.example.do_an_toeic.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManageNewsVO {
    private Integer id;
    private String name;
    private String image;
    private Integer type;
    private String createdBy;
    private Integer numberAccess;
}

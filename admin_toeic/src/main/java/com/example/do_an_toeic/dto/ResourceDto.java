package com.example.do_an_toeic.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceDto {

    private Integer id;
    private String createdBy;
    private String audio;


    private String image;


    private String fileName;


    private String typeResource;

    @NotNull(message = "Vui lòng chọn file")
    private MultipartFile multipartFile;
}

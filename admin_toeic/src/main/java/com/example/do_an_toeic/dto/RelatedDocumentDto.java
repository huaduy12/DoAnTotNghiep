package com.example.do_an_toeic.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelatedDocumentDto {
    private Integer id;
    @NotBlank(message = "Tên tài liệu không được để trống")
    private String name;
    @NotBlank(message = "Tiêu đề tài liệu không được để trống")
    private String title;
    private String createdBy;
    private String description;

    @NotNull(message = "Mức độ tài liệu không được để trống")
    private String levelToeic;

    private Integer numberAccess;

    private String fileName;
    private String linkImage;
    private MultipartFile fileImage;

}

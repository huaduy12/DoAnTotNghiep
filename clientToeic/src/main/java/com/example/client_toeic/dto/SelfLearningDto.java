package com.example.client_toeic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelfLearningDto {
    private Integer id;
    @NotBlank(message = "Tên bài học không được để trống")
    private String name;
    @NotBlank(message = "Tiêu đề không được để trống")
    private String title;
    private String createdBy;
    private String createdDate;
    @NotBlank(message = "Mô tả không được để trống")
    private String description;

    private Integer numberAccess;

    private String fileName;
    private String linkImage;
    private MultipartFile fileImage;
}

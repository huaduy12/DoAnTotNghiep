package com.example.client_toeic.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewsDto {
    private Integer id;

    @NotBlank(message ="Tên không được để trống")
    private String name;

    @NotBlank(message = "Vui lòng nhập tiêu đề")
    private String title;

    @NotBlank(message = "Vui lòng nhập mô tả")
    private String description;
    private String createdBy;
    private Integer numberAccess;

    private String fileName;
    private String linkImage;
    private MultipartFile fileImage;
}

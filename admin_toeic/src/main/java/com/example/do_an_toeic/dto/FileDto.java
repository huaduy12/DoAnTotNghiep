package com.example.do_an_toeic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDto {

    private String fileName;
    @NotNull(message = "Vui lòng chọn ảnh")
    private MultipartFile image;
}

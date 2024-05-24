package com.example.do_an_toeic.dto.exam;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamImportQuestionDto {
    private Integer id;
    @NotBlank(message = "Tên đề thi không được để trống")
    private String name;
    @NotNull(message = "Vui lòng nhập thời gian thi")
    @Range(min = 1,message = "Thời gian làm bài là >= 1 phút")
    private Integer time;
    private Double price;
    @NotNull(message = "Vui lòng chọn file")
    private MultipartFile multipartFile;

    @NotBlank(message = "Link ảnh không được để trống")
    private String linkImage;

    @NotBlank(message = "Mô tả không được để trống")
    private String title;
}

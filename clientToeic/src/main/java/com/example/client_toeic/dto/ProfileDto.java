package com.example.client_toeic.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {
    private Integer id;
    @NotBlank(message = "Vui lòng nhập username")
    private String userName;
    private String fullName;
    private String phoneNumber;
    @NotNull(message = "Vui lòng nhập email")
    @Email(message = "Vui lòng nhập email đúng định dạng")
    private String email;
    private String linkImage;
    private String nameImage;
}

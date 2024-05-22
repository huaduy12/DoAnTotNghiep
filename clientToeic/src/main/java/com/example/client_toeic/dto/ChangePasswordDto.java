package com.example.client_toeic.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordDto {

    private Integer id;

    @NotBlank(message = "Vui lòng nhập mật khẩu cũ")
    private String currentPassword;
    @NotBlank(message = "Vui lòng nhập mật khẩu mới")
    private String newPassword;
}

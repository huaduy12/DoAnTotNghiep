package com.example.client_toeic.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDto {

    @Length(min = 7,message = "Vui lòng nhập từ 8 ký tự trở lên")
    private String userName;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    private String email;

    @Size(min = 7,message = "Vui lòng nhập mật khẩu từ 8 ký tự trở lên")
    private String password;

}

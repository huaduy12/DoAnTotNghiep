package com.example.do_an_toeic.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordDto {
  @NotBlank(message = "Thông tin mật khẩu không được để trống")
  private String oldPassword;
  @NotBlank(message = "Mật khẩu mới không được để trống")
  @Length(min = 8,message = "Mật khẩu phải có ít nhất 8 ký tự trở lên")
  private String newPassword;
  @NotBlank(message = "Xác nhận mật khẩu mới không được để trống")
  private String confirmPassword;
}

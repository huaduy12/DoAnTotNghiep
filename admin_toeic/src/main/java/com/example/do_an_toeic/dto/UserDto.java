package com.example.do_an_toeic.dto;

import com.example.do_an_toeic.entity.Role;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Integer id;
    @NotBlank(message = "Username không được để trống")
    @Length(min = 7,message = "Vui lòng nhập từ 8 ký tự trở lên")
    private String userName;
    private String fullName;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    private String email;

    private String address;

    @Pattern(regexp = "^[0-9]{10}$", message = "Số điện thoại phải đúng định đạng")
    private String phoneNumber;

    @Past(message = "Ngày sinh phải trong quá khứ")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDay;

    private boolean active;

    private List<Role> roles;
}

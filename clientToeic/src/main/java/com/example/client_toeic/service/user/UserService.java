package com.example.client_toeic.service.user;

import com.example.client_toeic.dto.UserDto;
import com.example.client_toeic.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {

    User getByUser(String username);
    User getByEmailActive(String email);

    UserDto getByUsernameDto(String username);

    boolean changePassword(String passOld, String passNew, String passConfirm, String username);

}

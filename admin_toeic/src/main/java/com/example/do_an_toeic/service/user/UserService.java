package com.example.do_an_toeic.service.user;

import com.example.do_an_toeic.dto.UserDto;
import com.example.do_an_toeic.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {

    User getByUser(String username);

    UserDto getByUsernameDto(String username);

    boolean changePassword(String passOld, String passNew, String passConfirm, String username);

}

package com.example.client_toeic.service.account;

import com.example.client_toeic.dto.RegisterUserDto;
import com.example.client_toeic.entity.User;
import com.example.client_toeic.dto.UserDto;

import java.util.List;

public interface AccountService {
    User saveUser(RegisterUserDto registerUserDto);
    User saveUserOAuth2(UserDto userDto);

    User findByUsername(String username);
    User findByEmail(String email);
    User findByPhoneNumber(String phoneNumber);

    User findById(Integer id);
    User findByUsernameExist(String usernameNew, String usernameOld);
    User findByEmailExist(String emailNew,String emailOld);
    User findByPhoneNumberExist(String phoneNumberNew,String phoneNumberOld);

    Boolean deleteUser(Integer id);

    Boolean noActiveUser(Integer id);

    Boolean activeUser(Integer id);

    String resetPasswordUser(User user);

}

package com.example.do_an_toeic.service.account;

import com.example.do_an_toeic.dto.UserDto;
import com.example.do_an_toeic.entity.User;

import java.util.List;

public interface AccountService {
    List<UserDto> findAllManager();
    User save(UserDto userDto);
    User saveUser(UserDto userDto);

    List<UserDto> findAllUser();
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

    Boolean resetPasswordUser(Integer id);

}

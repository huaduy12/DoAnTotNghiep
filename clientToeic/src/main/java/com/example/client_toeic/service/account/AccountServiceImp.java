package com.example.client_toeic.service.account;

import com.example.client_toeic.dto.RegisterUserDto;
import com.example.client_toeic.entity.Role;
import com.example.client_toeic.entity.User;
import com.example.client_toeic.repository.RoleRepository;
import com.example.client_toeic.repository.UserRepository;
import com.example.client_toeic.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.client_toeic.utils.Constants;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountServiceImp implements AccountService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User saveUser(RegisterUserDto userDto) {
            trim(userDto);
            User user = modelMapper.map(userDto, User.class);

            user.setRoles(rolesUser());
            // set pass mặc định
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setActive(true);
            user.setId(null);
            User user1 = userRepository.save(user);
            return user1;
    }

    @Override
    public User saveUserOAuth2(UserDto userDto) {
        if(userRepository.findByEmailAndProvider(userDto.getEmail(),"google") == null){
            User user = modelMapper.map(userDto, User.class);
            user.setRoles(Arrays.asList(findByRoleOAuth2()));
            user.setProvider("google");
            User user1 = userRepository.save(user);
            return user1;
        }
        return null;
    }


    @Override
    public User findByUsername(String username) {
        return userRepository.findByUserName((username));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException(("Không tìm thấy user")));
    }

    @Override
    public User findByUsernameExist(String usernameNew, String usernameOld) {
        return userRepository.findByUsernameExist(usernameNew, usernameOld);
    }

    @Override
    public User findByEmailExist(String emailNew, String emailOld) {
        return userRepository.findByEmailExist(emailNew, emailOld);
    }

    @Override
    public User findByPhoneNumberExist(String phoneNumberNew, String phoneNumberOld) {
        return userRepository.findByPhoneNumberExist(phoneNumberNew, phoneNumberOld);
    }

    @Override
    public Boolean deleteUser(Integer id) {
        User user = findById(id);
        user.setIsDeleted(true);
        userRepository.save(user);
        return true;
    }

    @Override
    public Boolean noActiveUser(Integer id) {
        User user = findById(id);
        user.setActive(false);
        userRepository.save(user);
        return true;
    }

    @Override
    public Boolean activeUser(Integer id) {
        User user = findById(id);
        user.setActive(true);
        userRepository.save(user);
        return true;
    }

    @Override
    public String resetPasswordUser(User user) {
        String passwordRandom = generateRandomString(10);
        user.setPassword(passwordEncoder.encode(passwordRandom));
        userRepository.save(user);
        return passwordRandom;
    }

    private List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        users = users.stream().sorted(Comparator.comparing(User::getCreatedDate).reversed()).collect(Collectors.toList());
        return modelMapper.map(users, new TypeToken<List<UserDto>>() {
        }.getType());
    }

    private List<Role> rolesManager() {
        Role roleManager = roleRepository.findById(2).get();
        Role roleUser = roleRepository.findById(3).get();
        return Arrays.asList(roleManager, roleUser);
    }

    private List<Role> rolesUser() {
        Role roleUser = roleRepository.findById(3).get();
        return Arrays.asList(roleUser);
    }


    private void trim(RegisterUserDto user) {
        if (user.getUserName() != null) {
            user.setUserName(user.getUserName().trim());
        }
//        if (user.getFullName() != null) {
//            user.setFullName(user.getFullName().trim());
//        }
        if (user.getEmail() != null) {
            user.setEmail(user.getEmail().trim());
        }
//        if (user.getAddress() != null) {
//            user.setAddress(user.getAddress().trim());
//        }
//        if (user.getPhoneNumber() != null) {
//            user.setPhoneNumber(user.getPhoneNumber().trim());
//        }
        if (user.getPassword() != null) {
            user.setPassword(user.getPassword().trim());
        }
    }


    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);

        // Sinh ngẫu nhiên từng ký tự và thêm vào chuỗi
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }

    private Role findByRoleOAuth2(){
        return roleRepository.findById(4).get();
    }
}

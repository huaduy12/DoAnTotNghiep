package com.example.do_an_toeic.service.account;

import com.example.do_an_toeic.dto.UserDto;
import com.example.do_an_toeic.entity.Role;
import com.example.do_an_toeic.entity.User;
import com.example.do_an_toeic.repository.RoleRepository;
import com.example.do_an_toeic.repository.UserRepository;
import com.example.do_an_toeic.utils.Constants;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<UserDto> findAllManager() {
        List<UserDto> userDtos = findAll();
        List<UserDto> userDtoManagers = new ArrayList<>();
        Role roleManager = new Role(2, Constants.ROLE_MANAGER);
        Role roleAdmin = new Role(1, Constants.ROLE_ADMIN);

        for (UserDto userDto : userDtos) {
            if (userDto.getRoles().contains(roleManager) &&
                    !userDto.getRoles().contains(roleAdmin)) {
                userDtoManagers.add(userDto);
            }
        }
        return userDtoManagers;
    }

    @Override
    @Transactional
    public User save(UserDto userDto) {
        trim(userDto);
        if (userDto.getId() == null) {
            User user = modelMapper.map(userDto, User.class);

            user.setRoles(rolesManager());
            // set pass mặc định
            user.setPassword(passwordEncoder.encode(userDto.getUserName()));
            user.setActive(true);
            user.setId(null);
            User user1 = userRepository.save(user);
            return user1;
        } else {
            // cập nhập
            User userUpdate = findById(userDto.getId());
            userUpdate.setUserName(userDto.getUserName());
            userUpdate.setEmail(userDto.getEmail());
            userUpdate.setPhoneNumber(userDto.getPhoneNumber());
            userUpdate.setFullName(userDto.getFullName());
            userUpdate.setAddress(userDto.getAddress());
            userUpdate.setBirthDay(userDto.getBirthDay());
            User user = userRepository.save(userUpdate);
            return user;
        }
    }

    @Override
    public User saveUser(UserDto userDto) {
        trim(userDto);
        if (userDto.getId() == null) {
            User user = modelMapper.map(userDto, User.class);

            user.setRoles(rolesUser());
            // set pass mặc định
            user.setPassword(passwordEncoder.encode(userDto.getUserName()));
            user.setActive(true);
            user.setId(null);
            User user1 = userRepository.save(user);
            return user1;
        } else {
            // cập nhập
            User userUpdate = findById(userDto.getId());
            userUpdate.setUserName(userDto.getUserName());
            userUpdate.setEmail(userDto.getEmail());
            userUpdate.setPhoneNumber(userDto.getPhoneNumber());
            userUpdate.setFullName(userDto.getFullName());
            userUpdate.setAddress(userDto.getAddress());
            userUpdate.setBirthDay(userDto.getBirthDay());
            User user = userRepository.save(userUpdate);
            return user;
        }
    }

    @Override
    public List<UserDto> findAllUser() {
        List<UserDto> userDtos = findAll();
        List<UserDto> userDtoManagers = new ArrayList<>();
        Role roleManager = new Role(2, Constants.ROLE_MANAGER);
        Role roleUser = new Role(3, Constants.ROLE_USER);

        for (UserDto userDto : userDtos) {
            if (userDto.getRoles().contains(roleUser) &&
                    !userDto.getRoles().contains(roleManager)) {
                userDtoManagers.add(userDto);
            }
        }
        return userDtoManagers;

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
    public Boolean resetPasswordUser(Integer id) {
        User user = findById(id);
        user.setPassword(passwordEncoder.encode(user.getUserName()));
        userRepository.save(user);
        return true;
    }

    private List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        users = users.stream().sorted(Comparator.comparing(User::getCreatedDate).reversed()).collect(Collectors.toList());
        return modelMapper.map(users, new TypeToken<List<UserDto>>() {}.getType());
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


    private void trim(UserDto user) {
        if (user.getUserName() != null) {
            user.setUserName(user.getUserName().trim());
        }
        if (user.getFullName() != null) {
            user.setFullName(user.getFullName().trim());
        }
        if (user.getEmail() != null) {
            user.setEmail(user.getEmail().trim());
        }
        if (user.getAddress() != null) {
            user.setAddress(user.getAddress().trim());
        }
        if (user.getPhoneNumber() != null) {
            user.setPhoneNumber(user.getPhoneNumber().trim());
        }
    }
}

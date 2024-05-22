package com.example.client_toeic.service.user;


import com.example.client_toeic.dto.UserDto;
import com.example.client_toeic.entity.Role;
import com.example.client_toeic.entity.User;
import com.example.client_toeic.repository.UserRepository;
import com.example.client_toeic.security.EntityUserDetail;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public User getByUser(String username) {
        return userRepository.findByUserNameActive(username);
    }

    @Override
    public User getByEmailActive(String email) {
        return userRepository.findByEmailAndActive(email);
    }

    @Override
    public UserDto getByUsernameDto(String username) {
        User user = getByUser(username);
        UserDto userDto = null;
        if (user != null) {
            userDto = modelMapper.map(user, UserDto.class);
        }
        return userDto;
    }

    @Override
    public boolean changePassword(String passOld, String passNew, String passConfirm, String username) {
        User user = getByUser(username);
        passNew = passNew.trim();
        if (!passNew.equals(passConfirm)) {
            return false;
        } else {
            if (passwordEncoder.matches(passOld, user.getPassword())) {
                passNew = passwordEncoder.encode(passNew);
                user.setPassword(passNew);
                userRepository.save(user);
                return true;
            } else {
                return false;
            }
        }

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // security: lấy username từ database xong tý nữa so sánh
        User user = getByEmailActive(email);
        if (user != null) {
            // trả về entityUserDetail chứa user do mk tự đinh nghĩa
            return new EntityUserDetail(user);
        } else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        Collection<? extends GrantedAuthority> mapRoles =
                roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        return mapRoles;
    }


}

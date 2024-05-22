package com.example.client_toeic.service.profile;


import com.example.client_toeic.dto.ChangePasswordDto;
import com.example.client_toeic.dto.FileDto;
import com.example.client_toeic.dto.ImageProfileDto;
import com.example.client_toeic.dto.ProfileDto;
import com.example.client_toeic.entity.Resource;
import com.example.client_toeic.entity.User;
import com.example.client_toeic.repository.ResourceRepository;
import com.example.client_toeic.repository.UserRepository;
import com.example.client_toeic.service.firebase.ImageUploadService;
import com.example.client_toeic.utils.CommonUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImp implements ProfileService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ImageUploadService imageUploadService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ProfileDto getProfileUser(String username) {
        User user = userRepository.findByUserName(username);
        Resource resource = resourceRepository.findByUserId(user.getId());
        ProfileDto profileDto = modelMapper.map(user,ProfileDto.class);
        if(resource != null){
            profileDto.setNameImage(resource.getFileName() != null ? resource.getFileName() : null);
            profileDto.setLinkImage(resource.getImage() != null ? resource.getImage() : null);
        }
        return profileDto;
    }

    @Override
    public ProfileDto updateProfile(ProfileDto profileDto) {
        User user = userRepository.findById(profileDto.getId()).orElseThrow(()->new RuntimeException("Người dùng không tồn tại"));
        if(userRepository.findByUsernameExist(profileDto.getUserName(),user.getUserName()) != null){
            throw new RuntimeException("Username đã tồn tại");
        }
        if(userRepository.findByEmailExist(profileDto.getEmail(),user.getEmail()) != null){
            throw new RuntimeException("Email đã tồn tại");
        }
        if (!CommonUtils.isEmptyOrNull(profileDto.getPhoneNumber()) && userRepository.findByPhoneNumberExist(profileDto.getPhoneNumber(), user.getPhoneNumber()) != null) {
            throw new RuntimeException("Số điện thoại đã tồn tại");
        }
        user.setUserName(profileDto.getUserName());
        user.setFullName(profileDto.getFullName());
        user.setPhoneNumber(profileDto.getPhoneNumber());
        user.setEmail(profileDto.getEmail());

        return modelMapper.map(userRepository.save(user),ProfileDto.class);
    }

    @Override
    public ProfileDto updateImageProfile(ImageProfileDto imageProfileDto) {
        Resource resource = resourceRepository.findByUserId(imageProfileDto.getUserId());
        if(resource == null){
            Resource resource1 = new Resource();
            resource1.setFileName(imageProfileDto.getImage().getOriginalFilename());
            resource1.setUserId(imageProfileDto.getUserId());
            resource1.setImage(imageUploadService.upload(imageProfileDto.getImage()));
            Resource resource2 = resourceRepository.save(resource1);
            ProfileDto profileDto = new ProfileDto();
            profileDto.setLinkImage(resource2.getImage());
            profileDto.setNameImage(resource2.getFileName());
            return profileDto;
        }else{
            resource.setFileName(imageProfileDto.getImage().getOriginalFilename());
            String link = imageUploadService.upload(imageProfileDto.getImage());
            resource.setImage(link);
            Resource resource2 = resourceRepository.save(resource);
            ProfileDto profileDto = new ProfileDto();
            profileDto.setLinkImage(resource2.getImage());
            profileDto.setNameImage(resource2.getFileName());
            return profileDto;
        }
    }

    @Override
    public String changePassword(ChangePasswordDto changePasswordDto) {
        User user =userRepository.findById(changePasswordDto.getId()).orElseThrow(() ->new RuntimeException("Người dùng không tồn tại"));
        if(!passwordEncoder.matches(changePasswordDto.getCurrentPassword(),user.getPassword())){
            throw new RuntimeException("Mật khẩu cũ không chính xác");
        }
        user.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
        userRepository.save(user);
        return "Thành công";
    }


}

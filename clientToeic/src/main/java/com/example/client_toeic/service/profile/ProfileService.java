package com.example.client_toeic.service.profile;

import com.example.client_toeic.dto.ChangePasswordDto;
import com.example.client_toeic.dto.FileDto;
import com.example.client_toeic.dto.ImageProfileDto;
import com.example.client_toeic.dto.ProfileDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

public interface ProfileService {
    ProfileDto getProfileUser(String username);

    ProfileDto updateProfile(ProfileDto profileDto);

    ProfileDto updateImageProfile(ImageProfileDto imageProfileDto);

    String changePassword(ChangePasswordDto changePasswordDto);
}

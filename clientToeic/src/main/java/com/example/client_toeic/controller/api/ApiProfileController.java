package com.example.client_toeic.controller.api;

import com.example.client_toeic.dto.ChangePasswordDto;
import com.example.client_toeic.dto.ImageProfileDto;
import com.example.client_toeic.dto.ProfileDto;
import com.example.client_toeic.service.profile.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/profile")
public class ApiProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/update")
    private ResponseEntity<ProfileDto> updateProfile(@RequestBody ProfileDto profileDto){
        return ResponseEntity.ok(profileService.updateProfile(profileDto));
    }

    @PostMapping(value = "/update-image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    private ResponseEntity<ProfileDto> updateProfileImage(@ModelAttribute ImageProfileDto imageProfileDto){
        return ResponseEntity.ok(profileService.updateImageProfile(imageProfileDto));
    }


    @PostMapping("/change-password")
    private ResponseEntity<String> updateProfile(@Valid @RequestBody ChangePasswordDto changePasswordDto){
        return ResponseEntity.ok(profileService.changePassword(changePasswordDto));
    }


}

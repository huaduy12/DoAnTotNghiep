package com.example.client_toeic.controller.user;

import com.example.client_toeic.dto.ProfileDto;
import com.example.client_toeic.service.profile.ProfileService;
import com.example.client_toeic.utils.GetUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping()
    public String getProfile(Model model){

        ProfileDto profileDto = profileService.getProfileUser(GetUser.getUserName());
        model.addAttribute("profileDto",profileDto);
        return "profile/profile";
    }
}

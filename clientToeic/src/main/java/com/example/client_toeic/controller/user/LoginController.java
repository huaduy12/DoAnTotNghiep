package com.example.client_toeic.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/show-login-page")
    public String showLoginPage(){
        return "/login/login";
    }

//    @GetMapping("/contact")
//    public String showContactPage(){
//        return "contactLogin";
//    }
}

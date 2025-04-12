package com.example.do_an_toeic.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/show-login-page")
    public String showLoginPage(){
        return "/login/page-login";
    }

    @GetMapping("/contact")
    public String showContactPage(){
        return "/login/contact-login";
    }
}

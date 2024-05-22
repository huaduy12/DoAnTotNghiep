package com.example.client_toeic.controller.user;

import com.example.client_toeic.service.mail.SendMailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/client/forgot-password")
@Controller
public class ForgotPasswordController {

    @Autowired
    private SendMailService  sendMailService;

    @GetMapping()
    public String forgotPassword(Model model){
        return "login/forgot-password";
    }

}

package com.example.client_toeic.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorHandle {
    @GetMapping("/client/show-error")
    public String showPage403(){
        return "/login/404";
    }

}

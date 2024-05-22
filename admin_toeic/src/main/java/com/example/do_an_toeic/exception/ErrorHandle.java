package com.example.do_an_toeic.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorHandle {
    @GetMapping("/admin/show-error")
    public String showPage403(){
        return "/page/page-error-403";
    }

}

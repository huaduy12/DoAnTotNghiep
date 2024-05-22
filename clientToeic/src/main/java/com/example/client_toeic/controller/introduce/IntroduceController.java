package com.example.client_toeic.controller.introduce;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client/about")
public class IntroduceController {

    @GetMapping
    public String pageIntroduce(Model model){
        model.addAttribute("currentPage", "about");
        return "trang-chu/about";
    }
}

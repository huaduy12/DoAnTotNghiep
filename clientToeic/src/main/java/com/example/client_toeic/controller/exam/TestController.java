package com.example.client_toeic.controller.exam;

import com.example.client_toeic.dto.NewsDto;
import com.example.client_toeic.entity.Exam;
import com.example.client_toeic.enums.TypeExam;
import com.example.client_toeic.repository.ExamRepository;
import com.example.client_toeic.service.exam.ExamSkillService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/client/test")
public class TestController {

    @Autowired
    private ExamSkillService examSkillService;

    @Autowired
    private ExamRepository examRepository;

    @GetMapping
    public String trangChuTest(){
        return "test/test";
    }


    @GetMapping("/mini")
    public String pageNews(Model model, @RequestParam(value = "page",defaultValue = "1",required = false) Integer page){
        model.addAttribute("currentPage", "test");
        Page<Exam> newsPage = examSkillService.listExamMini(TypeExam.mini.getValue(), page);
        model.addAttribute("newsPage",newsPage);
        model.addAttribute("page",page);

        return "test/mini";
    }

    @GetMapping("/mini/{id}")
    public String detailMini(@PathVariable("id") Integer id){
        return "redirect:/client/test/mini/detail?id="+id;
    }

    @GetMapping("/mini/detail")
    public String showDetailMini(@RequestParam(value = "id",defaultValue = "31",required = false) Integer id, Model model){
        Exam exam = examRepository.findById(id).orElse(null);
        model.addAttribute("exam",exam);
        return "test/mini-test-detail";
    }
}

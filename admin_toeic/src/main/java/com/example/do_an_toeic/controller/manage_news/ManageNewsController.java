package com.example.do_an_toeic.controller.manage_news;

import com.example.do_an_toeic.dto.NewsDto;
import com.example.do_an_toeic.dto.UserDto;
import com.example.do_an_toeic.service.news.NewsService;
import com.example.do_an_toeic.utils.CommonUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/manage-news")
public class ManageNewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping()
    public String getAll(Model model){
        model.addAttribute("newsDto",newsService.getAll());

        return "quan-ly-tin-tuc/quan-ly-tin-tuc";
    }

    @GetMapping("/add")
    public String addNews(Model model){
        model.addAttribute("newsDto",new NewsDto());
        return "quan-ly-tin-tuc/them-tin-tuc";
    }

    @GetMapping("/{id}")
    public String updateNews(@PathVariable("id") String id, Model model){
        Integer idInt = null;
        try{
            idInt = Integer.parseInt(id);
            NewsDto newsDto = newsService.detailUpdate(idInt);
            if(newsDto == null){
                return "redirect:/admin/manage-news";
            }else{
                if(!CommonUtils.isEmptyOrNull(newsDto.getFileName())){
                    model.addAttribute("nameImage",newsDto.getFileName());
                }
                model.addAttribute("newsDto",newsDto);
            }

        }catch (Exception e){
            return "redirect:/admin/manage-news";
        }
        return "quan-ly-tin-tuc/them-tin-tuc";
    }

    @GetMapping("detail/{id}")
    public String detailNews(@PathVariable("id") String id, Model model){
        Integer idInt = null;
        try{
            idInt = Integer.parseInt(id);
            NewsDto newsDto = newsService.detailShow(idInt);
            if(newsDto == null){
                return "redirect:/admin/manage-news";
            }else{
//                if(!CommonUtils.isEmptyOrNull(newsDto.getFileName())){
//                    model.addAttribute("nameImage",newsDto.getFileName());
//                }
                model.addAttribute("newsDto",newsDto);
            }

        }catch (Exception e){
            return "redirect:/admin/manage-news";
        }
        return "quan-ly-tin-tuc/xem-chi-tiet";
    }


    @PostMapping("/save")
    public String saveAccountUser(@Valid @ModelAttribute("newsDto") NewsDto newsDto,
                                  BindingResult result, Model model,
                                  @ModelAttribute("nameImage") String nameImage,
                                  RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            return "quan-ly-tin-tuc/them-tin-tuc";
        }

        newsService.save(newsDto,nameImage);
        redirectAttributes.addFlashAttribute("changeSuccess","Thành công");
        return "redirect:/admin/manage-news";
    }

    @PostMapping("/delete")
    public String deleteNews(@ModelAttribute("idDelete") Integer idDelete, RedirectAttributes redirectAttributes){
        Boolean result = newsService.delete(idDelete);
        if(result){
            redirectAttributes.addFlashAttribute("changeSuccess","Thành công");
        }else{
            redirectAttributes.addFlashAttribute("changeFail","Thất bại");
        }
        return "redirect:/admin/manage-news";
    }
}

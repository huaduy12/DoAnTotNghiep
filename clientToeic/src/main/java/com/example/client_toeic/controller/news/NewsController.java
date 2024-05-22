package com.example.client_toeic.controller.news;

import com.example.client_toeic.dto.NewsDto;
import com.example.client_toeic.entity.News;
import com.example.client_toeic.service.news.NewsService;
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
@RequestMapping("/client/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping()
    public String pageNews(Model model, @RequestParam(value = "page",defaultValue = "1",required = false) Integer page){
        model.addAttribute("currentPage", "news");
        Page<NewsDto> newsPage = newsService.getAll(page);
        if(page.equals(1)){
            List<NewsDto> newsDtosTop6 = newsService.getNews6First();
            model.addAttribute("newsDtosTop6",newsDtosTop6);
            model.addAttribute("page1","page1");
        }
        model.addAttribute("newsPage",newsPage);
        model.addAttribute("page",page);

        return "trang-chu/news";
    }

    @GetMapping("/{id}")
    public String getNews(@PathVariable("id") String id, Model  model){
        model.addAttribute("currentPage", "news");
        int idInteger = 0;
        try{
            idInteger = Integer.parseInt(id);
        }catch (NumberFormatException n){
            return "redirect:/client/news";
        }
        NewsDto newsDto = newsService.findById(idInteger);
        if(newsDto == null){
            return "redirect:/client/news";
        }
        model.addAttribute("newsDto",newsDto);

        // tin tuc khác trừ tin tức đang xem
        List<NewsDto> newsDtosOther = newsService.newsOthers(idInteger);
        model.addAttribute("newsDtosOther",newsDtosOther);

        return "trang-chu/detail-news";
    }
}

package com.example.do_an_toeic.controller.manage_related_document;

import com.example.do_an_toeic.dto.NewsDto;
import com.example.do_an_toeic.dto.RelatedDocumentDto;
import com.example.do_an_toeic.service.news.NewsService;
import com.example.do_an_toeic.service.related_document.RelatedDocumentService;
import com.example.do_an_toeic.utils.CommonUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/manage-related-document")
public class ManageRelatedDocumentController {

    @Autowired
    private RelatedDocumentService relatedDocumentService;

    @GetMapping()
    public String getAll(Model model){
        model.addAttribute("relatedDocumentDtos",relatedDocumentService.getAll());

        return "quan-ly-tai-lieu/quan-ly-tai-lieu";
    }

    @GetMapping("/add")
    public String addRelatedDocument(Model model){
        model.addAttribute("relatedDocumentDto",new RelatedDocumentDto());
        return "quan-ly-tai-lieu/them-tai-lieu";
    }

    @GetMapping("/{id}")
    public String updateRelatedDocument(@PathVariable("id") String id, Model model){
        Integer idInt = null;
        try{
            idInt = Integer.parseInt(id);
            RelatedDocumentDto relatedDocumentDto = relatedDocumentService.detailUpdate(idInt);
            if(relatedDocumentDto == null){
                System.out.println("null");
            }else{
                if(!CommonUtils.isEmptyOrNull(relatedDocumentDto.getFileName())){
                    model.addAttribute("nameImage",relatedDocumentDto.getFileName());
                }
                model.addAttribute("relatedDocumentDto",relatedDocumentDto);
            }

        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/admin/manage-related-document";
        }
        return "quan-ly-tai-lieu/them-tai-lieu";
    }

    @GetMapping("detail/{id}")
    public String detailRelatedDocument(@PathVariable("id") String id, Model model){
        Integer idInt = null;
        try{
            idInt = Integer.parseInt(id);
            RelatedDocumentDto relatedDocumentDto = relatedDocumentService.detailShow(idInt);
            if(relatedDocumentDto == null){
                return "redirect:/admin/manage-related-document";
            }else{
//                if(!CommonUtils.isEmptyOrNull(newsDto.getFileName())){
//                    model.addAttribute("nameImage",newsDto.getFileName());
//                }
                model.addAttribute("relatedDocumentDto",relatedDocumentDto);
            }

        }catch (Exception e){
            return "redirect:/admin/manage-related-document";
        }
        return "quan-ly-tai-lieu/xem-chi-tiet";
    }


    @PostMapping("/save")
    public String saveAccountUser(@Valid @ModelAttribute("relatedDocumentDto") RelatedDocumentDto relatedDocumentDto,
                                  BindingResult result, Model model,
                                  @ModelAttribute("nameImage") String nameImage,
                                  RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            return "quan-ly-tai-lieu/them-tai-lieu";
        }

        relatedDocumentService.save(relatedDocumentDto,nameImage);
        redirectAttributes.addFlashAttribute("changeSuccess","Thành công");
        return "redirect:/admin/manage-related-document";
    }

    @PostMapping("/delete")
    public String deleteNews(@ModelAttribute("idDelete") Integer idDelete, RedirectAttributes redirectAttributes){
        Boolean result = relatedDocumentService.delete(idDelete);
        if(result){
            redirectAttributes.addFlashAttribute("changeSuccess","Thành công");
        }else{
            redirectAttributes.addFlashAttribute("changeFail","Thất bại");
        }
        return "redirect:/admin/manage-related-document";
    }
}

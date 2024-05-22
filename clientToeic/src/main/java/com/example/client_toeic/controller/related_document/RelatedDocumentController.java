package com.example.client_toeic.controller.related_document;

import com.example.client_toeic.dto.RelatedDocumentDto;
import com.example.client_toeic.service.related_document.RelatedDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client/related-document")
public class RelatedDocumentController {

    @Autowired
    private RelatedDocumentService relatedDocumentService;


    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Integer id, Model model){
        RelatedDocumentDto relatedDocumentDto = relatedDocumentService.detailShow(id);
        model.addAttribute("relatedDocumentDto",relatedDocumentDto);
        model.addAttribute("relatedDocumentOtherDto",relatedDocumentService.findAllByOther(id));
        return "related_document/detail_related_document";
    }
}

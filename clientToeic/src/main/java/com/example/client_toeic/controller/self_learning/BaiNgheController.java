package com.example.client_toeic.controller.self_learning;

import com.example.client_toeic.dto.SelfLearningDto;
import com.example.client_toeic.enums.LevelToeic;
import com.example.client_toeic.enums.TypeExercise;
import com.example.client_toeic.service.related_document.RelatedDocumentService;
import com.example.client_toeic.service.self_learning.SelfLearningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/client/self-learning/listening")
public class BaiNgheController {

    @Autowired
    private SelfLearningService selfLearningService;

    @Autowired
    private RelatedDocumentService relatedDocumentService;

    @GetMapping()
    public String pageNews(Model model, @RequestParam(value = "page",defaultValue = "1",required = false) Integer page){
        model.addAttribute("currentPage","self-learning");
        Page<SelfLearningDto> selfLearningDtos = selfLearningService.getAll(TypeExercise.TUHOC_BAINGHE.getValue(),page);
        model.addAttribute("selfLearningDtos",selfLearningDtos);
        model.addAttribute("page",page);
        return "/tu-hoc/self-learning-listening";
    }

    @GetMapping("/{id}")
    public String pageNews(@PathVariable("id") Integer id, Model model){
        model.addAttribute("currentPage","self-learning");
        SelfLearningDto selfLearningDto = selfLearningService.detail(id);
        model.addAttribute("selfLearningDto",selfLearningDto);
        model.addAttribute("selfLearningDtoOthers",selfLearningService.getLearningOther(TypeExercise.TUHOC_BAINGHE.getValue(), id));
        model.addAttribute("documentType400",relatedDocumentService.findAllByType(LevelToeic.level400.getValue()));
        model.addAttribute("documentType550",relatedDocumentService.findAllByType(LevelToeic.level550.getValue()));
        model.addAttribute("documentType700",relatedDocumentService.findAllByType(LevelToeic.level700.getValue()));
        model.addAttribute("documentType850",relatedDocumentService.findAllByType(LevelToeic.level850.getValue()));
        return "/tu-hoc/detail-self-learning-listening";
    }
}

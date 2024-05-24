package com.example.client_toeic.controller.exam;

import com.example.client_toeic.entity.Exam;
import com.example.client_toeic.entity.Exercise;
import com.example.client_toeic.enums.LevelToeic;
import com.example.client_toeic.enums.TypeSkill;
import com.example.client_toeic.repository.ExamRepository;
import com.example.client_toeic.service.exam.ExamSkillService;
import com.example.client_toeic.service.related_document.RelatedDocumentService;
import com.example.client_toeic.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/client/skill")
public class SkillController {

    @Autowired
    private ExamSkillService examSkillService;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private RelatedDocumentService relatedDocumentService;


    @GetMapping
    public String trangChuSkill(Model model){
        model.addAttribute("currentPage","test");
        model.addAttribute("documentType400",relatedDocumentService.findAllByType(LevelToeic.level400.getValue()));
        model.addAttribute("documentType550",relatedDocumentService.findAllByType(LevelToeic.level550.getValue()));
        model.addAttribute("documentType700",relatedDocumentService.findAllByType(LevelToeic.level700.getValue()));
        model.addAttribute("documentType850",relatedDocumentService.findAllByType(LevelToeic.level850.getValue()));
        return "test/skill/skill";
    }

    @GetMapping("/list")
    public String getListSkill(@RequestParam(value = "skill",defaultValue = "photo") String skill,
                               @RequestParam(value = "page",required = false,defaultValue = "1") Integer page, Model model){
        model.addAttribute("currentPage","test");
        List<String> skillToeic = listSkillToeic();

        if(!skillToeic.contains(skill)){
            return "redirect:/client/home";
        }
        Page<Exam> examsPage = examSkillService.listExamSkill(skill,page);
        model.addAttribute("newsPage",examsPage);
        model.addAttribute("page",page);
        model.addAttribute("skill", !CommonUtils.isEmptyOrNull(skill) ? skill : "");
        model.addAttribute("documentType400",relatedDocumentService.findAllByType(LevelToeic.level400.getValue()));
        model.addAttribute("documentType550",relatedDocumentService.findAllByType(LevelToeic.level550.getValue()));
        model.addAttribute("documentType700",relatedDocumentService.findAllByType(LevelToeic.level700.getValue()));
        model.addAttribute("documentType850",relatedDocumentService.findAllByType(LevelToeic.level850.getValue()));
        return "test/list-skill";
    }

    /**
     * Bat dau làm bai
     * @return
     */
    @GetMapping("/test/{id}")
    public String testReview(@PathVariable("id") Integer id){

        Optional<Exam> examOptional = examRepository.findById(id);
        if(examOptional.isPresent()){
            Exam exam = examOptional.get();
            String skill  =exam.getTypeSkill();
            switch (skill){
                case "photo":
                    return "redirect:/client/skill/test/photo?id="+id;
                case "question_response":
                    return "redirect:/client/skill/test/question-response?id="+id;
                case "short_conversation":
                    return "redirect:/client/skill/test/short-conversation?id="+id;
                case "short_talk":
                    return "redirect:/client/skill/test/short-talk?id="+id;
                case "incomplete_sentence":
                    return "redirect:/client/skill/test/incomplete-sentence?id="+id;
                case "text_completion":
                    return "redirect:/client/skill/test/text-completion?id="+id;
                case "single_passage":
                    return "redirect:/client/skill/test/single-passage?id="+id;
                case "double_passage":
                    return "redirect:/client/skill/test/double-passage?id="+id;
                default:
                    return "redirect:/client/skill";
            }
        }else {
            return "redirect:/client/skill/list";
        }

    }

    @GetMapping("/test/photo")
    public String reviewPhoto(@RequestParam("id") Integer id, Model model){

        Exam exam = examRepository.findById(id).get();
        model.addAttribute("level",exam.getLevel());
        model.addAttribute("skill",exam.getTypeSkill());
        model.addAttribute("name",exam.getName());
        return "/test/skill/photo";
    }

    @GetMapping("/test/question-response")
    public String reviewQuestionResponse(@RequestParam("id") Integer id, Model model){
        Exam exam = examRepository.findById(id).get();
        model.addAttribute("level",exam.getLevel());
        model.addAttribute("skill",exam.getTypeSkill());
        model.addAttribute("name",exam.getName());
        return "/test/skill/question-response";
    }
    @GetMapping("/test/short-conversation")
    public String reviewShortConversation(@RequestParam("id") Integer id, Model model){
        Exam exam = examRepository.findById(id).get();
        model.addAttribute("level",exam.getLevel());
        model.addAttribute("skill",exam.getTypeSkill());
        model.addAttribute("name",exam.getName());
        return "/test/skill/short-conversation";
    }
    @GetMapping("/test/short-talk")
    public String reviewShortTalk(@RequestParam("id") Integer id, Model model){
        Exam exam = examRepository.findById(id).get();
        model.addAttribute("level",exam.getLevel());
        model.addAttribute("skill",exam.getTypeSkill());
        model.addAttribute("name",exam.getName());
        return "/test/skill/short-talk";
    }
    @GetMapping("/test/incomplete-sentence")
    public String reviewIncompleteSentence(@RequestParam("id") Integer id, Model model){
        Exam exam = examRepository.findById(id).get();
        model.addAttribute("level",exam.getLevel());
        model.addAttribute("skill",exam.getTypeSkill());
        model.addAttribute("name",exam.getName());
        return "/test/skill/incomplete-sentence";
    }
    @GetMapping("/test/text-completion")
    public String reviewTextCompletion(@RequestParam("id") Integer id, Model model){

        Exam exam = examRepository.findById(id).get();
        model.addAttribute("level",exam.getLevel());
        model.addAttribute("skill",exam.getTypeSkill());
        model.addAttribute("name",exam.getName());
        return "/test/skill/text-completion";
    }
    @GetMapping("/test/single-passage")
    public String reviewSinglePassage(@RequestParam("id") Integer id, Model model){

        Exam exam = examRepository.findById(id).get();
        model.addAttribute("level",exam.getLevel());
        model.addAttribute("skill",exam.getTypeSkill());
        model.addAttribute("name",exam.getName());
        return "/test/skill/single-passage";
    }

    @GetMapping("/test/double-passage")
    public String reviewDoublePassage(@RequestParam("id") Integer id, Model model){
        Exam exam = examRepository.findById(id).get();
        model.addAttribute("level",exam.getLevel());
        model.addAttribute("skill",exam.getTypeSkill());
        model.addAttribute("name",exam.getName());
        return "/test/skill/double-passage";
    }

    public List<String> listSkillToeic(){
        return new ArrayList<>(Arrays.asList(TypeSkill.PHOTO.getValue(),
                TypeSkill.QUESTION_RESPONSE.getValue(),
                TypeSkill.SHORT_CONVERSATION.getValue(),
                TypeSkill.SHORT_TALK.getValue(),
                TypeSkill.INCOMPLETE_SENTENCE.getValue(),
                TypeSkill.TEXT_COMPLETION.getValue(),
                TypeSkill.SINGLE_PASSAGE.getValue(),
                TypeSkill.DOUBLE_PASSAGE.getValue()));
    }

}

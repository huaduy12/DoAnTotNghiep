package com.example.do_an_toeic.controller.manage_exam;

import com.example.do_an_toeic.entity.Exam;
import com.example.do_an_toeic.entity.ExamQuestion;
import com.example.do_an_toeic.entity.User;
import com.example.do_an_toeic.repository.ExamQuestionRepository;
import com.example.do_an_toeic.repository.ExamRepository;
import com.example.do_an_toeic.service.exam.ExamSkillService;
import com.example.do_an_toeic.entity.Exercise;
import com.example.do_an_toeic.enums.TypeExam;
import com.example.do_an_toeic.enums.TypeExercise;
import com.example.do_an_toeic.repository.ExerciseQuestionRepository;
import com.example.do_an_toeic.repository.ExerciseRepository;
import com.example.do_an_toeic.service.exercise.ExerciseService;
import com.example.do_an_toeic.service.question.QuestionService;
import com.example.do_an_toeic.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = {"/admin/manage-skill","/admin/manage-skill/"})
public class ManageSkillCtrl {

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private ExerciseQuestionRepository exerciseQuestionRepository;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ExamSkillService examSkillService;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private ExamQuestionRepository examQuestionRepository;

    @GetMapping()
    public String trangChu(Model model){
        model.addAttribute("examSkill",examSkillService.findAll(TypeExam.skill.getValue()));
        return "quan-ly-thi/quan-ly-skill";
    }


    @GetMapping("detail/{id}")
    public String getDetail(@PathVariable("id") Integer id,Model model){

        Exam exam = null;
        Optional<Exam> optionalExam = examRepository.findById(id);
        if(optionalExam.isPresent()){
            exam = optionalExam.get();
        }else{
            return "redirect:/admin/manage-skill";
        }
        // nếu dạng 1 câu hỏi, xem chi tiết những bài ôn tập có 1 câu hỏi
        String typeSkill = exam.getTypeSkill();
        if(TypeExercise.PHOTO.getValue().equals(typeSkill) ||
                TypeExercise.QUESTION_RESPONSE.getValue().equals(typeSkill) ||
                TypeExercise.SHORT_CONVERSATION.getValue().equals(typeSkill) ||
                TypeExercise.INCOMPLETE_SENTENCE.getValue().equals(typeSkill)){

            model.addAttribute("detailExamPhotoDto",examSkillService.detailQuestionPhoto(exam));
            return "quan-ly-thi/detail-skill-photo";
        }else {
            // nếu dạng nhiều câu hỏi
            model.addAttribute("detailExamShortTalkDto",examSkillService.detailQuestionShortTalk(exam));
            return "quan-ly-thi/detail-skill-short-talk";
        }
    }


    @PostMapping("/delete")
    public String delete(@ModelAttribute("idDelete") Integer idExam, RedirectAttributes redirectAttributes){
        Optional<Exam> examOptional = examRepository.findById(idExam);
        if(examOptional.isPresent()){
            Exam exam = examOptional.get();
            List<ExamQuestion> examQuestions = examQuestionRepository.findAllByExamId(exam.getId());
            examQuestionRepository.deleteAll(examQuestions);
            examRepository.delete(exam);
            redirectAttributes.addFlashAttribute("changeSuccess","Thành công");
        }else {
            redirectAttributes.addFlashAttribute("deleteFail","Thất bại");
        }
        return "redirect:/admin/manage-skill";
    }

    @PostMapping("/no-active")
    public String noActiveAccount(@ModelAttribute("idNoActive") Integer id,
                                  Model model, RedirectAttributes redirectAttributes){
        Exam exam = examRepository.findById(id).orElse(null);
        if(exam == null){
            redirectAttributes.addFlashAttribute("changeFail","Thay đổi thất bại");
        }
        else{
            exam.setIsActive(false);
            examRepository.save(exam);
            redirectAttributes.addFlashAttribute("changeSuccess","Thành công");
        }
        String type = exam.getTypeExam();
        if(TypeExam.skill.getValue().equals(type)){
            return "redirect:/admin/manage-skill";
        }else if(TypeExam.mini.getValue().equals(type)){
            return "redirect:/admin/manage-mini-test";
        }else {
            return "redirect:/admin/manage-full-test";
        }
    }

    @PostMapping("/active")
    public String activeAccount(@ModelAttribute("idActive") Integer id,
                                Model model, RedirectAttributes redirectAttributes){
        Exam exam = examRepository.findById(id).orElse(null);
        if(exam == null){
            redirectAttributes.addFlashAttribute("changeFail","Thay đổi thất bại");
        }
        else{
            exam.setIsActive(true);
            examRepository.save(exam);
            redirectAttributes.addFlashAttribute("changeSuccess","Thành công");
        }
        String typeAc = exam.getTypeExam();
        if(TypeExam.skill.getValue().equals(typeAc)){
            return "redirect:/admin/manage-skill";
        }else if(TypeExam.mini.getValue().equals(typeAc)){
            return "redirect:/admin/manage-mini-test";
        }else {
            return "redirect:/admin/manage-full-test";
        }
    }
}

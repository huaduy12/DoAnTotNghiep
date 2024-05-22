package com.example.do_an_toeic.controller.manage_excercise;

import com.example.do_an_toeic.dto.DetailExercisePhotoDto;
import com.example.do_an_toeic.dto.QuestionExerciseDto;
import com.example.do_an_toeic.dto.QuestionPhotoDto;
import com.example.do_an_toeic.entity.Exercise;
import com.example.do_an_toeic.entity.Question;
import com.example.do_an_toeic.enums.TypeExercise;
import com.example.do_an_toeic.repository.ExerciseQuestionRepository;
import com.example.do_an_toeic.repository.ExerciseRepository;
import com.example.do_an_toeic.service.exercise.ExerciseService;
import com.example.do_an_toeic.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = {"/admin/manage-exercise","/admin/manage-exercise/"})
public class ManageExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private ExerciseQuestionRepository exerciseQuestionRepository;

    @Autowired
    private QuestionService questionService;

    @GetMapping()
    public String trangChu(Model model){
        model.addAttribute("exercises",exerciseService.findAll());
        return "on-tap/quan-ly-on-tap";
    }


    @PostMapping("/delete")
    private String deleteExercise(@ModelAttribute("idDelete") Integer id, RedirectAttributes redirectAttributes){
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        if(exercise.isPresent()){
            exerciseService.deleteExercise(exercise.get());
            redirectAttributes.addFlashAttribute("changeSuccess","Thành công");
        }else{
            redirectAttributes.addFlashAttribute("deleteFail","Thất bại");
        }
        return "redirect:/admin/manage-exercise";
    }


    @GetMapping("detail/{id}")
    public String getDetail(@PathVariable("id") Integer id,Model model){

        Exercise exercise = null;
        Optional<Exercise> optionalExercise = exerciseRepository.findById(id);
        if(optionalExercise.isPresent()){
            exercise = optionalExercise.get();
        }else{
            return "redirect:/admin/manage-exercise";
        }
        // nếu dạng 1 câu hỏi, xem chi tiết những bài ôn tập có 1 câu hỏi
        String typeSkill = exercise.getTypeSkill();
        if(TypeExercise.PHOTO.getValue().equals(typeSkill) ||
           TypeExercise.QUESTION_RESPONSE.getValue().equals(typeSkill) ||
           TypeExercise.SHORT_CONVERSATION.getValue().equals(typeSkill) ||
           TypeExercise.INCOMPLETE_SENTENCE.getValue().equals(typeSkill)){

            model.addAttribute("detailExercisePhotoDto",exerciseService.detailQuestionPhoto(exercise));
            return "on-tap/detail-photo";
        }else {
            // nếu dạng nhiều câu hỏi
            model.addAttribute("detailExerciseShortTalkDto",exerciseService.detailQuestionShortTalk(exercise));
            return "on-tap/detail-short-talk";
        }

    }

}

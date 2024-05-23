package com.example.client_toeic.controller.review;

import com.example.client_toeic.entity.Exercise;
import com.example.client_toeic.enums.LevelToeic;
import com.example.client_toeic.enums.TypeSkill;
import com.example.client_toeic.repository.ExerciseRepository;
import com.example.client_toeic.service.exercise.ExerciseService;
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
@RequestMapping("/client/review")
public class OnTapController {

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @GetMapping()
    public String trangOnTap(Model model){
        model.addAttribute("currentPage","review");
        return "review/review";
    }

    @GetMapping("/get")
    public String skillTungLevel(@RequestParam(required = false,name = "level") String level, Model model){
        model.addAttribute("currentPage","review");
        List<String> levelToeic = new ArrayList<>(Arrays.asList(LevelToeic.level400.getValue(),
                LevelToeic.level550.getValue(),
                LevelToeic.level700.getValue(),
                LevelToeic.level850.getValue()));
        if(!levelToeic.contains(level)){
            return "redirect:/client/review";
        }
        model.addAttribute("level",level);
        return "review/review-level";
    }

    @GetMapping("list")
    public String getListSkill(@RequestParam("level") String level, @RequestParam("skill") String skill,
                               @RequestParam(value = "page",required = false,defaultValue = "1") Integer page, Model model){
        model.addAttribute("currentPage","review");
        List<String> levelToeic = listLevelToeic();
        List<String> skillToeic = listSkillToeic();
        if(!levelToeic.contains(level)){
            return "redirect:/client/review";
        }
        if(!skillToeic.contains(skill)){
            return "redirect:/client/review";
        }
        Page<Exercise>  exercisePage = exerciseService.responseListExercise(skill,level, page);
        model.addAttribute("newsPage",exercisePage);
        model.addAttribute("page",page);
        model.addAttribute("skill", !CommonUtils.isEmptyOrNull(skill) ? skill : "");
        model.addAttribute("level", !CommonUtils.isEmptyOrNull(level) ? level : "");
        return "review/list-review-level";
    }

    /**
     * Bat dau làm bai
     * @return
     */
    @GetMapping("/test/{id}")
    public String testReview(@PathVariable("id") Integer id){
        Optional<Exercise> exerciseOptional = exerciseRepository.findById(id);
        if(exerciseOptional.isPresent()){
            Exercise exercise = exerciseOptional.get();
            String skill  =exercise.getTypeSkill();
            switch (skill){
                case "photo":
                    return "redirect:/client/review/test/photo?id="+id;
                case "question_response":
                    return "redirect:/client/review/test/question-response?id="+id;
                case "short_conversation":
                    return "redirect:/client/review/test/short-conversation?id="+id;
                case "short_talk":
                    return "redirect:/client/review/test/short-talk?id="+id;
                case "incomplete_sentence":
                    return "redirect:/client/review/test/incomplete-sentence?id="+id;
                case "text_completion":
                    return "redirect:/client/review/test/text-completion?id="+id;
                case "single_passage":
                    return "redirect:/client/review/test/single-passage?id="+id;
                case "double_passage":
                    return "redirect:/client/review/test/double-passage?id="+id;
                default:
                    return "redirect:/client/review";
            }
        }else {
            return "redirect:/client/review";
        }

    }




    @GetMapping("/test/photo")
    public String reviewPhoto(@RequestParam("id") Integer id, Model model){
        Exercise exercise = exerciseRepository.findById(id).get();
        model.addAttribute("level",exercise.getLevel());
        model.addAttribute("skill",exercise.getTypeSkill());
        model.addAttribute("name",exercise.getName());
        return "review/photo";
    }

    @GetMapping("/test/question-response")
    public String reviewQuestionResponse(@RequestParam("id") Integer id, Model model){
        Exercise exercise = exerciseRepository.findById(id).get();
        model.addAttribute("level",exercise.getLevel());
        model.addAttribute("skill",exercise.getTypeSkill());
        model.addAttribute("name",exercise.getName());
        return "review/question-response";
    }
    @GetMapping("/test/short-conversation")
    public String reviewShortConversation(@RequestParam("id") Integer id, Model model){
        Exercise exercise = exerciseRepository.findById(id).get();
        model.addAttribute("level",exercise.getLevel());
        model.addAttribute("skill",exercise.getTypeSkill());
        model.addAttribute("name",exercise.getName());
        return "review/short-conversation";
    }
    @GetMapping("/test/short-talk")
    public String reviewShortTalk(@RequestParam("id") Integer id, Model model){
        Exercise exercise = exerciseRepository.findById(id).get();
        model.addAttribute("level",exercise.getLevel());
        model.addAttribute("skill",exercise.getTypeSkill());
        model.addAttribute("name",exercise.getName());
        return "review/short-talk";
    }
    @GetMapping("/test/incomplete-sentence")
    public String reviewIncompleteSentence(@RequestParam("id") Integer id, Model model){
        Exercise exercise = exerciseRepository.findById(id).get();
        model.addAttribute("level",exercise.getLevel());
        model.addAttribute("skill",exercise.getTypeSkill());
        model.addAttribute("name",exercise.getName());
        return "review/incomplete-sentence";
    }
    @GetMapping("/test/text-completion")
    public String reviewTextCompletion(@RequestParam("id") Integer id, Model model){

        Exercise exercise = exerciseRepository.findById(id).get();
        model.addAttribute("level",exercise.getLevel());
        model.addAttribute("skill",exercise.getTypeSkill());
        model.addAttribute("name",exercise.getName());
        return "/review/text-completion";
    }
    @GetMapping("/test/single-passage")
    public String reviewSinglePassage(@RequestParam("id") Integer id, Model model){

        Exercise exercise = exerciseRepository.findById(id).get();
        model.addAttribute("level",exercise.getLevel());
        model.addAttribute("skill",exercise.getTypeSkill());
        model.addAttribute("name",exercise.getName());
        return "review/single-passage";
    }

    @GetMapping("/test/double-passage")
    public String reviewDoublePassage(@RequestParam("id") Integer id, Model model){
        Exercise exercise = exerciseRepository.findById(id).get();
        model.addAttribute("level",exercise.getLevel());
        model.addAttribute("skill",exercise.getTypeSkill());
        model.addAttribute("name",exercise.getName());
        return "review/double-passage";
    }

    public List<String> listLevelToeic(){
        return new ArrayList<>(Arrays.asList(LevelToeic.level400.getValue(),
                LevelToeic.level550.getValue(),
                LevelToeic.level700.getValue(),
                LevelToeic.level850.getValue()));
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

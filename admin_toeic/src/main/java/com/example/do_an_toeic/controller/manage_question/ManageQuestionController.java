package com.example.do_an_toeic.controller.manage_question;

import com.example.do_an_toeic.dto.ChoiceQuestionDto;
import com.example.do_an_toeic.dto.FileDto;
import com.example.do_an_toeic.dto.QuestionPhotoDto;
import com.example.do_an_toeic.entity.ExamQuestion;
import com.example.do_an_toeic.entity.ExerciseQuestion;
import com.example.do_an_toeic.entity.Question;
import com.example.do_an_toeic.enums.TypeExercise;
import com.example.do_an_toeic.repository.ExamQuestionRepository;
import com.example.do_an_toeic.repository.ExerciseQuestionRepository;
import com.example.do_an_toeic.repository.QuestionRepository;
import com.example.do_an_toeic.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = {"/admin/manage-question","/admin/manage-question/"})
public class ManageQuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ExamQuestionRepository examQuestionRepository;

    @Autowired
    private ExerciseQuestionRepository exerciseQuestionRepository;

    @Autowired
    private QuestionRepository questionRepository;


    @GetMapping()
    public String manageQuestion(Model model){
        ChoiceQuestionDto choiceQuestionDto = new ChoiceQuestionDto();
        model.addAttribute("choiceQuestionDto", choiceQuestionDto);
        model.addAttribute("questions",questionService.finAll());
        model.addAttribute("file",new FileDto());
        return "cau-hoi/quan-li-cau-hoi";
    }

    @PostMapping("/delete")
    public String deleteNews(@ModelAttribute("idDelete") Integer idDelete, RedirectAttributes redirectAttributes){

        List<ExamQuestion> examQuestionRepositories = examQuestionRepository.findAllByQuestionId(idDelete);
        if(!examQuestionRepositories.isEmpty()){
            redirectAttributes.addFlashAttribute("deleteFailExam","Thất bại");
            return "redirect:/admin/manage-question";
        }
        List<ExerciseQuestion> exerciseQuestions = exerciseQuestionRepository.findAllByQuestionId(idDelete);
        if(!exerciseQuestions.isEmpty()){
            redirectAttributes.addFlashAttribute("deleteFailExercise","Thất bại");
            return "redirect:/admin/manage-question";
        }
        Optional<Question> questionOptional = questionRepository.findById(idDelete);
        if(questionOptional.isPresent()){
            Question question = questionOptional.get();
            questionService.delete(question);
            redirectAttributes.addFlashAttribute("deleteSuccess","Xóa thành công");
        }else{
            redirectAttributes.addFlashAttribute("deleteFail","Xóa Thất bại");
        }
        return "redirect:/admin/manage-question";
    }

    /**
     * chuyển hướng màn hình qua các câu hỏi phù hợp với loại kỹ năng khi thêm mới
     * @param model
     * @return
     */
    @PostMapping("/question")
    public String postQuestion(Model model, @ModelAttribute("choiceQuestionDto") ChoiceQuestionDto choiceQuestionDto){
        String levelToeic = choiceQuestionDto.getLevelToeic();
        String skillToeic = choiceQuestionDto.getSkillToeic();
        Integer typeQuestion = choiceQuestionDto.getTypeQuestion();

        // photo
        if(TypeExercise.PHOTO.getValue().equals(skillToeic)){
            return "redirect:/admin/manage-question/question/photo?level="+levelToeic+"&type="+typeQuestion;
        } else if (TypeExercise.QUESTION_RESPONSE.getValue().equals(skillToeic)) {
            return "redirect:/admin/manage-question/question/question-response?level="+levelToeic+"&type="+typeQuestion;
        } else if (TypeExercise.SHORT_CONVERSATION.getValue().equals(skillToeic)) {
            return "redirect:/admin/manage-question/question/short-conversation?level="+levelToeic+"&type="+typeQuestion;
        } else if (TypeExercise.SHORT_TALK.getValue().equals(skillToeic)) {
            return "redirect:/admin/manage-question/question/short-talk?level="+levelToeic+"&type="+typeQuestion;
        }else if (TypeExercise.INCOMPLETE_SENTENCE.getValue().equals(skillToeic)) {
            return "redirect:/admin/manage-question/question/incomplete?level="+levelToeic+"&type="+typeQuestion;
        }else if (TypeExercise.TEXT_COMPLETION.getValue().equals(skillToeic)) {
            return "redirect:/admin/manage-question/question/text-completion?level="+levelToeic+"&type="+typeQuestion;
        }else if (TypeExercise.SINGLE_PASSAGE.getValue().equals(skillToeic)) {
            return "redirect:/admin/manage-question/question/single-passage?level="+levelToeic+"&type="+typeQuestion;
        }else if (TypeExercise.DOUBLE_PASSAGE.getValue().equals(skillToeic)) {
            return "redirect:/admin/manage-question/question/double-passage?level="+levelToeic+"&type="+typeQuestion;
        }

        return "cau-hoi/quan-li-cau-hoi";
    }


    /**
     * chuyển hướng khi cập nhật
     * @param id
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/update/{id}")
    public String detailUpdate(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes){
        Optional<Question> questionOptional = questionRepository.findById(id);
        if(questionOptional.isPresent()){
            Question question = questionOptional.get();
            String skillToeic = question.getTypeSkill();
            if(TypeExercise.PHOTO.getValue().equals(skillToeic)){
                return "redirect:/admin/manage-question/question/update-photo?id="+question.getId();
            } else if (TypeExercise.QUESTION_RESPONSE.getValue().equals(skillToeic)) {
                return "redirect:/admin/manage-question/question/update-question-response?id="+question.getId();
            } else if (TypeExercise.SHORT_CONVERSATION.getValue().equals(skillToeic)) {
                return "redirect:/admin/manage-question/question/update-short-conversation?id="+question.getId();
            } else if (TypeExercise.SHORT_TALK.getValue().equals(skillToeic)) {
                return "redirect:/admin/manage-question/question/update-short-talk?id="+question.getId();
            }else if (TypeExercise.INCOMPLETE_SENTENCE.getValue().equals(skillToeic)) {
                return "redirect:/admin/manage-question/question/update-incomplete?id="+question.getId();
            }else if (TypeExercise.TEXT_COMPLETION.getValue().equals(skillToeic)) {
                return "redirect:/admin/manage-question/question/update-text-completion?id="+question.getId();
            }else if (TypeExercise.SINGLE_PASSAGE.getValue().equals(skillToeic)) {
                return "redirect:/admin/manage-question/question/update-single-passage?id="+question.getId();
            }else if (TypeExercise.DOUBLE_PASSAGE.getValue().equals(skillToeic)) {
                return "redirect:/admin/manage-question/question/update-double-passage?id="+question.getId();
            }
        }else{
            redirectAttributes.addFlashAttribute("updateFail","Không tìm thấy câu hỏi");
            return "redirect:/admin/manage-question";
        }
        return "redirect:/admin/manage-question";
    }

    /**
     * Xem chi tiết
     * @param id
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/question/{id}")
    public String detailShow(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes){
        Optional<Question> questionOptional = questionRepository.findById(id);
        if(questionOptional.isPresent()){
            Question question = questionOptional.get();
            String skillToeic = question.getTypeSkill();
            if(TypeExercise.PHOTO.getValue().equals(skillToeic)){
                return "redirect:/admin/manage-question/question/detail-photo?id="+question.getId();
            } else if (TypeExercise.QUESTION_RESPONSE.getValue().equals(skillToeic)) {
                return "redirect:/admin/manage-question/question/detail-question-response?id="+question.getId();
            } else if (TypeExercise.SHORT_CONVERSATION.getValue().equals(skillToeic)) {
                return "redirect:/admin/manage-question/question/detail-short-conversation?id="+question.getId();
            } else if (TypeExercise.SHORT_TALK.getValue().equals(skillToeic)) {
                return "redirect:/admin/manage-question/question/detail-short-talk?id="+question.getId();
            }else if (TypeExercise.INCOMPLETE_SENTENCE.getValue().equals(skillToeic)) {
                return "redirect:/admin/manage-question/question/detail-incomplete?id="+question.getId();
            }else if (TypeExercise.TEXT_COMPLETION.getValue().equals(skillToeic)) {
                return "redirect:/admin/manage-question/question/detail-text-completion?id="+question.getId();
            }else if (TypeExercise.SINGLE_PASSAGE.getValue().equals(skillToeic)) {
                return "redirect:/admin/manage-question/question/detail-single-passage?id="+question.getId();
            }else if (TypeExercise.DOUBLE_PASSAGE.getValue().equals(skillToeic)) {
                return "redirect:/admin/manage-question/question/detail-double-passage?id="+question.getId();
            }
        }else{
            redirectAttributes.addFlashAttribute("updateFail","Không tìm thấy câu hỏi");
            return "redirect:/admin/manage-question";
        }
        return "redirect:/admin/manage-question";
    }



    @GetMapping("/question/photo")
    public String questionPhoto(Model model){
        return "cau-hoi/question-photo";
    }

    @GetMapping("/question/update-photo")
    public String questionupdatePhoto(Model model){
        return "cau-hoi/update-photo";
    }

    @GetMapping("/question/detail-photo")
    public String questionDetailPhoto(@RequestParam("id") Integer id, Model model){
        model.addAttribute("questionPhotoDto",questionService.detailQuestionPhoto(id));
        return "cau-hoi/detail-photo";
    }
//end
    @GetMapping("/question/question-response")
    public String questionResponse(Model model){
        return "cau-hoi/question-response";
    }

    @GetMapping("/question/update-question-response")
    public String questionupdateQuestionResponse(Model model){
        return "cau-hoi/update-question-response";
    }

    @GetMapping("/question/detail-question-response")
    public String questionDetailQuestionResponse(@RequestParam("id") Integer id, Model model){
        model.addAttribute("questionPhotoDto",questionService.detailQuestionPhoto(id));
        return "cau-hoi/detail-question-response";
    }

//    end
    @GetMapping("/question/short-conversation")
    public String questionShortConversation(Model model){
        return "/cau-hoi/short-conversation";
    }

    @GetMapping("/question/update-short-conversation")
    public String questionUpdateShortConversation(Model model){
        return "/cau-hoi/update-short-conversation";
    }

    @GetMapping("/question/detail-short-conversation")
    public String questionDetailShortConversation(@RequestParam("id") Integer id, Model model){
        model.addAttribute("questionPhotoDto",questionService.detailQuestionPhoto(id));
        return "cau-hoi/detail-short-conversation";
    }

    // end

    @GetMapping("/question/short-talk")
    public String questionShortTalk(Model model){
        return "cau-hoi/short-talk";
    }

    @GetMapping("/question/update-short-talk")
    public String questionUpdateShortTalk(Model model){
        return "/cau-hoi/update-short-talk";
    }

    @GetMapping("/question/detail-short-talk")
    public String questionDetailShortTalk(@RequestParam("id") Integer id, Model model){
        model.addAttribute("questionPhotoDto",questionService.detailQuestionShortTalk(id));
        return "cau-hoi/detail-short-talk";
    }

    // end
    @GetMapping("/question/incomplete")
    public String questionIncomplete(Model model){
        return "/cau-hoi/incomplete";
    }

    @GetMapping("/question/update-incomplete")
    public String questionUpdateIncomplete(Model model){
        return "/cau-hoi/update-incomplete";
    }

    @GetMapping("/question/detail-incomplete")
    public String questionDetailIncomplete(@RequestParam("id") Integer id, Model model){
        model.addAttribute("questionPhotoDto",questionService.detailQuestionPhoto(id));
        return "cau-hoi/detail-incomplete";
    }
    //end
    @GetMapping("/question/text-completion")
    public String questionTextCompletion(Model model){
        return "cau-hoi/text-completion";
    }

    @GetMapping("/question/update-text-completion")
    public String questionUpdateTextCompletion(Model model){
        return "cau-hoi/update-text-completion";
    }

    @GetMapping("/question/detail-text-completion")
    public String questionDetailTextCompletion(@RequestParam("id") Integer id, Model model){
        model.addAttribute("questionPhotoDto",questionService.detailQuestionShortTalk(id));
        return "cau-hoi/detail-text-completion";
    }

    // end
    @GetMapping("/question/single-passage")
    public String questionSinglePassage(Model model){
        return "cau-hoi/single-passage";
    }

    @GetMapping("/question/update-single-passage")
    public String questionUpdateSinglePassage(Model model){
        return "cau-hoi/update-single-passage";
    }

    @GetMapping("/question/detail-single-passage")
    public String questionDetailSinglePassage(@RequestParam("id") Integer id, Model model){
        model.addAttribute("questionPhotoDto",questionService.detailQuestionShortTalk(id));
        return "cau-hoi/detail-single-passage";
    }

//    end
    @GetMapping("/question/double-passage")
    public String questionDoublePassage(Model model){
        return "cau-hoi/double-passage";
    }

    @GetMapping("/question/update-double-passage")
    public String questionUpdateDoublePassage(Model model){
        return "cau-hoi/update-double-passage";
    }

    @GetMapping("/question/detail-double-passage")
    public String questionDetailDoublePassage(@RequestParam("id") Integer id, Model model){
        model.addAttribute("questionPhotoDto",questionService.detailQuestionShortTalk(id));
        return "cau-hoi/detail-double-passage";
    }

}

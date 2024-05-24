package com.example.do_an_toeic.controller.manage_exam;

import com.example.do_an_toeic.dto.UserDto;
import com.example.do_an_toeic.dto.exam.ExamImportQuestionDto;
import com.example.do_an_toeic.entity.Exam;
import com.example.do_an_toeic.enums.TypeExam;
import com.example.do_an_toeic.repository.ExamRepository;
import com.example.do_an_toeic.service.exam.ExamSkillService;
import com.example.do_an_toeic.service.import_file.ImportQuestionService;
import com.example.do_an_toeic.utils.CommonUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("admin/manage-exam")
public class ManageExamCtrl {

    @Autowired
    private ExamSkillService examSkillService;

    @Autowired
    private ImportQuestionService importQuestionService;
    @Autowired
    private ExamRepository examRepository;
    // xóa kỹ năng, đề thi mini, full, lưu ý đối với đề thi mất phí thi ko cho xóa chi được đóng bài


    @GetMapping("/mini")
    public String getMinitest(Model model){
        List<Exam> exams = examRepository.findAllByTypeExam(TypeExam.mini.getValue());
        model.addAttribute("exams",exams);
        ExamImportQuestionDto examImportQuestionDto = new ExamImportQuestionDto();
        model.addAttribute("examImportQuestionDto",examImportQuestionDto);
        return "quan-ly-thi/quan-ly-mini";
    }

    @PostMapping("/mini/save")
    public String saveMiniTest(@Valid @ModelAttribute("examImportQuestionDto") ExamImportQuestionDto examImportQuestionDto,
                               BindingResult result, Model model,RedirectAttributes redirectAttributes) throws IOException {
        if(CommonUtils.isEmptyOrNull(examImportQuestionDto.getMultipartFile().getOriginalFilename())){
            result.addError(new FieldError("examImportQuestionDto","multipartFile","Vui lòng nhập file"));
        }
        if(result.hasErrors()){
            List<Exam> exams = examRepository.findAllByTypeExam(TypeExam.mini.getValue());
            model.addAttribute("exams",exams);
            model.addAttribute("formErrorAdd","Có lỗi ở form");
            return "quan-ly-thi/quan-ly-mini";

        }
        try{
            examSkillService.importExamMini(examImportQuestionDto);
            redirectAttributes.addFlashAttribute("importSuccess","Thành công");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("importFail","Thất bại");
        }
        return "redirect:/admin/manage-exam/mini";
    }
}

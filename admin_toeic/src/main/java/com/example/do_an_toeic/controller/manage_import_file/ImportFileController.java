package com.example.do_an_toeic.controller.manage_import_file;

import com.example.do_an_toeic.dto.FileDto;
import com.example.do_an_toeic.service.import_file.ImportQuestionService;
import com.example.do_an_toeic.utils.CommonUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/admin/import-question")
public class ImportFileController {

    @Autowired
    private ImportQuestionService importQuestionService;

    // import câu hỏi
    @PostMapping("/save")
    public String importFile(@Valid @ModelAttribute("file") FileDto fileDto, RedirectAttributes redirectAttributes) throws IOException {
        if(fileDto != null){
            try {
                Integer result = importQuestionService.importFileQuestion(fileDto.getImage());
                redirectAttributes.addFlashAttribute("successImport","Import câu hỏi thành công");
            }catch (Exception e){
                redirectAttributes.addFlashAttribute("errorImport","Lỗi ở phần import photo");
            }
        }
        return "redirect:/admin/manage-question";
    }
}

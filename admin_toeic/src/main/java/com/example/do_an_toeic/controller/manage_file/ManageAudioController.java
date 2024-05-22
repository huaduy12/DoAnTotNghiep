package com.example.do_an_toeic.controller.manage_file;

import com.example.do_an_toeic.dto.FileDto;
import com.example.do_an_toeic.dto.NewsDto;
import com.example.do_an_toeic.dto.ResourceDto;
import com.example.do_an_toeic.entity.Resource;
import com.example.do_an_toeic.enums.TypeResource;
import com.example.do_an_toeic.service.resource.ResourceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("admin/manage-file/audio")
public class ManageAudioController {
    @Autowired
    private ResourceService resourceService;

    @GetMapping()
    public String getAllImage(Model model) {
        List<ResourceDto> resourceDtos = resourceService.getResourceAudio();
        model.addAttribute("resourceDtos", resourceDtos);
        ResourceDto resourceDto = new ResourceDto();
        model.addAttribute("resourceDto", resourceDto);
        return "quan-ly-file/file-audio";
    }

    @PostMapping("/save")
    public String saveImage(@Valid @ModelAttribute("resourceDto") ResourceDto resourceDto,
                            BindingResult result, Model model,
                            RedirectAttributes redirectAttributes){
        if(resourceDto.getId() == null && resourceDto.getMultipartFile().getOriginalFilename().equals("")){
            result.addError(new FieldError("resourceDto","multipartFile","Vui lòng chọn file"));
        }
        if(result.hasErrors()){
            List<ResourceDto> resourceDtos = resourceService.getResourceImage();
            model.addAttribute("resourceDtos", resourceDtos);
            model.addAttribute("formError","Có lỗi ở form");
            return "quan-ly-file/file-audio";
        }
        if(resourceDto.getId() != null && resourceDto.getMultipartFile().getOriginalFilename().equals("")){
            // không thay đổi
            return "redirect:/admin/manage-file/audio";
        }else{
            Resource resource = resourceService.saveFile(resourceDto, TypeResource.AUDIO.getValue());
            if(resource != null){
                redirectAttributes.addFlashAttribute("changeSuccess","Thành công");
            }else{
                redirectAttributes.addFlashAttribute("changeFail","Thất bại");
            }
            return "redirect:/admin/manage-file/audio";
        }

    }
    @PostMapping("/delete")
    public String deleteImage(@ModelAttribute("idDelete") Integer id,Model model, RedirectAttributes redirectAttributes) {
        Boolean result = resourceService.deleteFile(id);
        if(result){
            redirectAttributes.addFlashAttribute("changeSuccess","Thành công");
        }else{
            redirectAttributes.addFlashAttribute("changeFail","Thất bại");
        }
        return "redirect:/admin/manage-file/audio";
    }
}

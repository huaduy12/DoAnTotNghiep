package com.example.do_an_toeic.controller.manage_self_learning;

import com.example.do_an_toeic.dto.RelatedDocumentDto;
import com.example.do_an_toeic.dto.SelfLearningDto;
import com.example.do_an_toeic.enums.TypeExercise;
import com.example.do_an_toeic.service.self_learning.SelfLearningService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/manage-self-learning")
public class SelfLearningController {

    @Autowired
    private SelfLearningService selfLearningService;

    @GetMapping("/vocabulary")
    public String getAllTuVung(Model model){
        List<SelfLearningDto> selfLearningDtos = selfLearningService.getAll(TypeExercise.TUHOC_TUVUNG.getValue());
        model.addAttribute("selfLearningDtos",selfLearningDtos);
        return "tu-hoc/quan-ly-tu-vung";
    }

    @GetMapping("/vocabulary/add")
    public String themTuVung(Model model){
        SelfLearningDto selfLearningDto = new SelfLearningDto();
        model.addAttribute("selfLearningDto",selfLearningDto);
        return "tu-hoc/them-tu-vung";
    }
    @GetMapping("/vocabulary/{id}")
    public String getTuVung(@PathVariable("id") String id, Model model){
        Integer idInt = null;
        try{
            idInt = Integer.parseInt(id);
            SelfLearningDto selfLearningDto = selfLearningService.detailShowUpdate(idInt);
            if(selfLearningDto == null){
                return "redirect:/admin/manage-self-learning/vocabulary";
            }else{

                model.addAttribute("filename",selfLearningDto.getFileName());
                model.addAttribute("selfLearningDto",selfLearningDto);
            }
        }catch (Exception e){
            return "redirect:/admin/manage-self-learning/vocabulary";
        }
        return "tu-hoc/them-tu-vung";
    }


    @PostMapping("/vocabulary/save")
    public String saveAccountUser(@Valid @ModelAttribute("selfLearningDto") SelfLearningDto selfLearningDto,
                                  BindingResult result, Model model,
                                  @ModelAttribute("nameImage") String nameImage,
                                  RedirectAttributes redirectAttributes){

        if(result.hasErrors()){

            model.addAttribute("filename",selfLearningDto.getFileImage().getOriginalFilename());
            return "tu-hoc/them-tu-vung";
        }
        selfLearningService.save(selfLearningDto,nameImage, TypeExercise.TUHOC_TUVUNG.getValue());
        redirectAttributes.addFlashAttribute("changeSuccess","Thành công");
        return "redirect:/admin/manage-self-learning/vocabulary";
    }

    @PostMapping("vocabulary/delete")
    public String deleteNews(@ModelAttribute("idDelete") Integer idDelete, RedirectAttributes redirectAttributes){
        Boolean result = selfLearningService.delete(idDelete);
        if(result){
            redirectAttributes.addFlashAttribute("changeSuccess","Thành công");
        }else{
            redirectAttributes.addFlashAttribute("changeFail","Thất bại");
        }
        return "redirect:/admin/manage-self-learning/vocabulary";
    }

    // ngu pháp
    @GetMapping("/grammar")
    public String getAllNguPhap(Model model){
        List<SelfLearningDto> selfLearningDtos = selfLearningService.getAll(TypeExercise.TUHOC_NGUPHAP.getValue());
        model.addAttribute("selfLearningDtos",selfLearningDtos);
        return "tu-hoc/quan-ly-ngu-phap";
    }
    @GetMapping("/grammar/add")
    public String themNguPhap(Model model){
        SelfLearningDto selfLearningDto = new SelfLearningDto();
        model.addAttribute("selfLearningDto",selfLearningDto);
        return "tu-hoc/them-ngu-phap";
    }
    @GetMapping("/grammar/{id}")
    public String getNguPhap(@PathVariable("id") String id, Model model){
        Integer idInt = null;
        try{
            idInt = Integer.parseInt(id);
            SelfLearningDto selfLearningDto = selfLearningService.detailShowUpdate(idInt);
            if(selfLearningDto == null){
                return "redirect:/admin/manage-self-learning/grammar";
            }else{

                model.addAttribute("filename",selfLearningDto.getFileName());
                model.addAttribute("selfLearningDto",selfLearningDto);
            }
        }catch (Exception e){
            return "redirect:/admin/manage-self-learning/grammar";
        }
        return "tu-hoc/them-ngu-phap";
    }


    @PostMapping("/grammar/save")
    public String saveNguPhap(@Valid @ModelAttribute("selfLearningDto") SelfLearningDto selfLearningDto,
                                  BindingResult result, Model model,
                                  @ModelAttribute("nameImage") String nameImage,
                                  RedirectAttributes redirectAttributes){
        System.out.println(selfLearningDto);
        System.out.println(selfLearningDto.getFileImage().getOriginalFilename());
        System.out.println(nameImage);
        if(result.hasErrors()){
            return "tu-hoc/them-ngu-phap";
        }
        selfLearningService.save(selfLearningDto,nameImage, TypeExercise.TUHOC_NGUPHAP.getValue());
        redirectAttributes.addFlashAttribute("changeSuccess","Thành công");
        return "redirect:/admin/manage-self-learning/grammar";
    }

    @PostMapping("grammar/delete")
    public String deleteNguPhap(@ModelAttribute("idDelete") Integer idDelete, RedirectAttributes redirectAttributes){
        Boolean result = selfLearningService.delete(idDelete);
        if(result){
            redirectAttributes.addFlashAttribute("changeSuccess","Thành công");
        }else{
            redirectAttributes.addFlashAttribute("changeFail","Thất bại");
        }
        return "redirect:/admin/manage-self-learning/grammar";
    }

// end
    // start
    @GetMapping("/listening")
    public String getAllBaiNghe(Model model){
        List<SelfLearningDto> selfLearningDtos = selfLearningService.getAll(TypeExercise.TUHOC_BAINGHE.getValue());
        model.addAttribute("selfLearningDtos",selfLearningDtos);
        return "tu-hoc/quan-ly-bai-nghe";
    }
    @GetMapping("/listening/add")
    public String themBaiNghe(Model model){
        SelfLearningDto selfLearningDto = new SelfLearningDto();
        model.addAttribute("selfLearningDto",selfLearningDto);
        return "tu-hoc/them-bai-nghe";
    }
    @GetMapping("/listening/{id}")
    public String getBaiNghe(@PathVariable("id") String id, Model model){
        Integer idInt = null;
        try{
            idInt = Integer.parseInt(id);
            SelfLearningDto selfLearningDto = selfLearningService.detailShowUpdate(idInt);
            if(selfLearningDto == null){
                return "redirect:/admin/manage-self-learning/listening";
            }else{

                model.addAttribute("filename",selfLearningDto.getFileName());
                model.addAttribute("selfLearningDto",selfLearningDto);
            }
        }catch (Exception e){
            return "redirect:/admin/manage-self-learning/listening";
        }
        return "tu-hoc/them-bai-nghe";
    }


    @PostMapping("/listening/save")
    public String saveBaiNghe(@Valid @ModelAttribute("selfLearningDto") SelfLearningDto selfLearningDto,
                              BindingResult result, Model model,
                              @ModelAttribute("nameImage") String nameImage,
                              RedirectAttributes redirectAttributes){
        System.out.println("1: " + selfLearningDto);
        if(result.hasErrors()){
            return "tu-hoc/them-bai-nghe";
        }
        selfLearningService.save(selfLearningDto,nameImage, TypeExercise.TUHOC_BAINGHE.getValue());
        redirectAttributes.addFlashAttribute("changeSuccess","Thành công");
        return "redirect:/admin/manage-self-learning/listening";
    }

    @PostMapping("listening/delete")
    public String deleteBaiNghe(@ModelAttribute("idDelete") Integer idDelete, RedirectAttributes redirectAttributes){
        Boolean result = selfLearningService.delete(idDelete);
        if(result){
            redirectAttributes.addFlashAttribute("changeSuccess","Thành công");
        }else{
            redirectAttributes.addFlashAttribute("changeFail","Thất bại");
        }
        return "redirect:/admin/manage-self-learning/grammar";
    }

    // end
    @GetMapping("/reading")
    public String getAllBaiDoc(Model model){
        List<SelfLearningDto> selfLearningDtos = selfLearningService.getAll(TypeExercise.TUHOC_BAIDOC.getValue());
        model.addAttribute("selfLearningDtos",selfLearningDtos);
        return "tu-hoc/quan-ly-bai-doc";
    }
    @GetMapping("/reading/add")
    public String themBaiDoc(Model model){
        SelfLearningDto selfLearningDto = new SelfLearningDto();
        model.addAttribute("selfLearningDto",selfLearningDto);
        return "tu-hoc/them-bai-doc";
    }
    @GetMapping("/reading/{id}")
    public String getBaiDoc(@PathVariable("id") String id, Model model){
        Integer idInt = null;
        try{
            idInt = Integer.parseInt(id);
            SelfLearningDto selfLearningDto = selfLearningService.detailShowUpdate(idInt);
            if(selfLearningDto == null){
                return "redirect:/admin/manage-self-learning/reading";
            }else{

                model.addAttribute("filename",selfLearningDto.getFileName());
                model.addAttribute("selfLearningDto",selfLearningDto);
            }
        }catch (Exception e){
            return "redirect:/admin/manage-self-learning/reading";
        }
        return "tu-hoc/them-bai-doc";
    }


    @PostMapping("/reading/save")
    public String saveBaiDoc(@Valid @ModelAttribute("selfLearningDto") SelfLearningDto selfLearningDto,
                              BindingResult result, Model model,
                              @ModelAttribute("nameImage") String nameImage,
                              RedirectAttributes redirectAttributes){

        if(result.hasErrors()){
            return "tu-hoc/them-bai-doc";
        }
        selfLearningService.save(selfLearningDto,nameImage, TypeExercise.TUHOC_BAIDOC.getValue());
        redirectAttributes.addFlashAttribute("changeSuccess","Thành công");
        return "redirect:/admin/manage-self-learning/reading";
    }

    @PostMapping("reading/delete")
    public String deleteBaiDoc(@ModelAttribute("idDelete") Integer idDelete, RedirectAttributes redirectAttributes){
        Boolean result = selfLearningService.delete(idDelete);
        if(result){
            redirectAttributes.addFlashAttribute("changeSuccess","Thành công");
        }else{
            redirectAttributes.addFlashAttribute("changeFail","Thất bại");
        }
        return "redirect:/admin/manage-self-learning/reading";
    }

    @GetMapping("/{id}")
    public String showDetail(@PathVariable("id") Integer id,Model model){
        SelfLearningDto selfLearningDto = selfLearningService.detail(id);
        model.addAttribute("selfLearningDto",selfLearningDto);
        return "tu-hoc/xem-chi-tiet";
    }
}

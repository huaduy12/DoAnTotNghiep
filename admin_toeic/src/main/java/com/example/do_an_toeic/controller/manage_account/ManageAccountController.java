package com.example.do_an_toeic.controller.manage_account;


import com.example.do_an_toeic.dto.UserDto;
import com.example.do_an_toeic.entity.User;
import com.example.do_an_toeic.service.account.AccountService;
import com.example.do_an_toeic.utils.CommonUtils;
import com.example.do_an_toeic.utils.Constants;
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
@RequestMapping("/admin/manage-account")
public class ManageAccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/manager")
    public String homeAccount(Model model){
        List<UserDto> userDtos = accountService.findAllManager();

        model.addAttribute("userForm",new UserDto());
        model.addAttribute("userDtos",userDtos);
        return "/page/user_manager";
    }

    @GetMapping("/user")
    public String homeAccountUser(Model model){
        List<UserDto> userDtos = accountService.findAllUser();

        model.addAttribute("userForm",new UserDto());
        model.addAttribute("userDtos",userDtos);
        return "/page/user_client";
    }

    /**
     * luu tai khoan nguoi dung
     * @param userDto
     * @param result
     * @param model
     * @param form
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/save-user")
    public String saveAccountUser(@Valid @ModelAttribute("userForm") UserDto userDto,
                              BindingResult result, Model model,
                              @ModelAttribute("form") String form,
                              RedirectAttributes redirectAttributes){
        if(userDto.getId() ==null){
            User userName = accountService.findByUsername(userDto.getUserName());
            if(userName != null){
                result.addError(new FieldError("userForm","userName","Tên đăng nhập đã tồn tại"));
            }
            User email = accountService.findByEmail(userDto.getEmail());
            if(email != null){
                result.addError(new FieldError("userForm","email","Email đã được đăng ký"));
            }
            User phoneNumber = accountService.findByPhoneNumber(userDto.getPhoneNumber());
            if(phoneNumber != null){
                result.addError(new FieldError("userForm","phoneNumber","Số điện thoại đã được đăng ký"));
            }
        }else{
            // cập nhập
            User user = accountService.findById(userDto.getId());
            if(!user.getUserName().equals(userDto.getUserName()) &&
                    accountService.findByUsernameExist(userDto.getUserName(),user.getUserName()) != null){
                result.addError(new FieldError("userForm","userName","Tên đăng nhập đã tồn tại"));
            }

            if(!user.getEmail().equals(userDto.getEmail()) &&
                    accountService.findByEmailExist(userDto.getEmail(),user.getEmail()) != null){
                result.addError(new FieldError("userForm","email","Email đã được đăng ký"));
            }
            if(!user.getPhoneNumber().equals(userDto.getPhoneNumber()) &&
                    accountService.findByPhoneNumberExist(userDto.getPhoneNumber(),user.getPhoneNumber()) != null){
                result.addError(new FieldError("userForm","phoneNumber","Số điện thoại đã được đăng ký"));
            }
        }
        if(result.hasErrors()){
            List<UserDto> userDtos = accountService.findAllUser();
            model.addAttribute("userDtos",userDtos);

            if(!form.trim().equals("") && userDto.getId() == null){
                model.addAttribute("formErrorAdd","Có lỗi ở form");
            }
            if(!form.trim().equals("") && userDto.getId() != null){
                model.addAttribute("formErrorEdit","Có lỗi ở form");
            }
            return "/page/user_manager";
        }
        if(userDto.getId()!= null){
            User user = accountService.saveUser(userDto);
            redirectAttributes.addFlashAttribute("changeSuccess","Tạo thành công, mật khẩu mặc định là username");
            // update
        }else{
            // create
            User user = accountService.saveUser(userDto);
            redirectAttributes.addFlashAttribute("createSuccess","Tạo thành công, mật khẩu mặc định là username");
        }
        return "redirect:/admin/manage-account/user";

    }


    /**
     *luu tai khoan mangeger
     * @param userDto
     * @param result
     * @param model
     * @param form
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/save")
    public String saveAccount(@Valid @ModelAttribute("userForm") UserDto userDto,
                              BindingResult result, Model model,
                              @ModelAttribute("form") String form,
                              RedirectAttributes redirectAttributes){
//        System.out.println(userDto);
//        System.out.println(form);
        if(userDto.getId() ==null){
            User userName = accountService.findByUsername(userDto.getUserName());
            if(userName != null){
                result.addError(new FieldError("userForm","userName","Tên đăng nhập đã tồn tại"));
            }
            User email = accountService.findByEmail(userDto.getEmail());
            if(email != null){
                result.addError(new FieldError("userForm","email","Email đã được đăng ký"));
            }
            User phoneNumber = accountService.findByPhoneNumber(userDto.getPhoneNumber());
            if(phoneNumber != null){
                result.addError(new FieldError("userForm","phoneNumber","Số điện thoại đã được đăng ký"));
            }
        }else{
            // cập nhập
            User user = accountService.findById(userDto.getId());
            if(!user.getUserName().equals(userDto.getUserName()) &&
                    accountService.findByUsernameExist(userDto.getUserName(),user.getUserName()) != null){
                result.addError(new FieldError("userForm","userName","Tên đăng nhập đã tồn tại"));
            }

            if(!user.getEmail().equals(userDto.getEmail()) &&
                    accountService.findByEmailExist(userDto.getEmail(),user.getEmail()) != null){
                result.addError(new FieldError("userForm","email","Email đã được đăng ký"));
            }
            if(!user.getPhoneNumber().equals(userDto.getPhoneNumber()) &&
                    accountService.findByPhoneNumberExist(userDto.getPhoneNumber(),user.getPhoneNumber()) != null){
                result.addError(new FieldError("userForm","phoneNumber","Số điện thoại đã được đăng ký"));
            }
        }
        if(result.hasErrors()){
            List<UserDto> userDtos = accountService.findAllManager();
            model.addAttribute("userDtos",userDtos);

            if(!form.trim().equals("") && userDto.getId() == null){
                model.addAttribute("formErrorAdd","Có lỗi ở form");
            }
            if(!form.trim().equals("") && userDto.getId() != null){
                model.addAttribute("formErrorEdit","Có lỗi ở form");
            }
            return "/page/user_manager";
        }
        if(userDto.getId()!= null){
            User user = accountService.save(userDto);
            redirectAttributes.addFlashAttribute("changeSuccess","Tạo thành công, mật khẩu mặc định là username");
            // update
        }else{
            // create
            User user = accountService.save(userDto);
            redirectAttributes.addFlashAttribute("createSuccess","Tạo thành công, mật khẩu mặc định là username");

        }
        return "redirect:/admin/manage-account/manager";

    }


    @PostMapping("/delete")
    public String deleteAccount(@ModelAttribute("idDelete") Integer id,
                                @ModelAttribute("type") String type,
                                Model model, RedirectAttributes redirectAttributes){
        User user = accountService.findById(id);
        if(user == null){
            redirectAttributes.addFlashAttribute("changeFail","Thay đổi thất bại");
        }
        else{
            accountService.deleteUser(id);
            redirectAttributes.addFlashAttribute("changeSuccess","Thành công");
        }
        if(type.equals(Constants.TYPE_MANAGER)){
            return "redirect:/admin/manage-account/manager";
        }else {
            return "redirect:/admin/manage-account/user";
        }

    }

    @PostMapping("/no-active")
    public String noActiveAccount(@ModelAttribute("idNoActive") Integer id,
                                  @ModelAttribute("type") String type,
                                  Model model, RedirectAttributes redirectAttributes){
        User user = accountService.findById(id);
        if(user == null){
            redirectAttributes.addFlashAttribute("changeFail","Thay đổi thất bại");
        }
        else{
            accountService.noActiveUser(id);
            redirectAttributes.addFlashAttribute("changeSuccess","Thành công");
        }

        if(type.equals(Constants.TYPE_MANAGER)){
            return "redirect:/admin/manage-account/manager";
        }else {
            return "redirect:/admin/manage-account/user";
        }
    }

    @PostMapping("/active")
    public String activeAccount(@ModelAttribute("idActive") Integer id,
                                @ModelAttribute("type") String type,
                                Model model, RedirectAttributes redirectAttributes){
        User user = accountService.findById(id);
        if(user == null){
            redirectAttributes.addFlashAttribute("changeFail","Thay đổi thất bại");
        }
        else{
            accountService.activeUser(id);
            redirectAttributes.addFlashAttribute("changeSuccess","Thành công");
        }

        if(type.equals(Constants.TYPE_MANAGER)){
            return "redirect:/admin/manage-account/manager";
        }else {
            return "redirect:/admin/manage-account/user";
        }
    }

    @PostMapping("/reset-pass")
    public String resetPassAccount(@ModelAttribute("idReset") Integer id,
                                   @ModelAttribute("type") String type,
                                   Model model, RedirectAttributes redirectAttributes){
        User user = accountService.findById(id);
        if(user == null){
            redirectAttributes.addFlashAttribute("changeFail","Thay đổi thất bại");
        }
        else{
            accountService.resetPasswordUser(id);
            redirectAttributes.addFlashAttribute("createSuccess","Thành công");
        }

        if(type.equals(Constants.TYPE_MANAGER)){
            return "redirect:/admin/manage-account/manager";
        }else {
            return "redirect:/admin/manage-account/user";
        }
    }
}

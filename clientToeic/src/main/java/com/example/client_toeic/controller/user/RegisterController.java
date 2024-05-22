package com.example.client_toeic.controller.user;

import com.example.client_toeic.dto.RegisterUserDto;
import com.example.client_toeic.dto.UserDto;
import com.example.client_toeic.entity.User;
import com.example.client_toeic.service.account.AccountService;
import com.example.client_toeic.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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

@RequestMapping("/client/register")
@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @GetMapping()
    public String register(Model model, HttpServletRequest request) {
        model.addAttribute("userForm", new RegisterUserDto());
        return "login/register";
    }

    @PostMapping("/save")
    public String saveAccountUser(@Valid @ModelAttribute("userForm") RegisterUserDto userDto,
                                  BindingResult result, Model model,
                                  @ModelAttribute("form") String form,
                                  RedirectAttributes redirectAttributes) {

        User userName = accountService.findByUsername(userDto.getUserName());
        if (userName != null) {
            result.addError(new FieldError("userForm", "userName", "Tên đăng nhập đã tồn tại"));
        }
        User email = accountService.findByEmail(userDto.getEmail());
        if (email != null) {
            result.addError(new FieldError("userForm", "email", "Email đã được đăng ký"));
        }

        if (result.hasErrors()) {
            model.addAttribute("userForm", userDto);
            redirectAttributes.addFlashAttribute("createFail", "Tạo thất bại");
            return "login/register";
        }
        User user = accountService.saveUser(userDto);
        redirectAttributes.addFlashAttribute("createSuccess", "Tạo thành công");

        return "redirect:/show-login-page";
    }

}

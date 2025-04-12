package com.example.do_an_toeic.controller.user;

import com.example.do_an_toeic.dto.user.ChangePasswordDto;
import com.example.do_an_toeic.service.user.UserService;
import com.example.do_an_toeic.utils.GetUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/redirect-password")
    public String redirectPassword(Model model) {
        model.addAttribute("changePasswordDto", new ChangePasswordDto());
        return "/user/change-password";
    }

    @PostMapping("/change-password")
    public String changePassword(@Valid @ModelAttribute("changePasswordDto") ChangePasswordDto changePasswordDto,
                                 RedirectAttributes redirectAttributes, Model model) {
        boolean success = userService.changePassword(changePasswordDto.getOldPassword(), changePasswordDto.getNewPassword(), changePasswordDto.getConfirmPassword(), GetUser.getUserName());
        if (success) {
            redirectAttributes.addFlashAttribute("changeSuccess", "Đổi mật khẩu thành công");
            return "redirect:/admin/user/redirect-password";
        } else {
            model.addAttribute("passwordIncorrect", "Thông tin mật khẩu không chính xác");
            return "user/change-password";
        }
    }
}

package com.example.do_an_toeic.controller.user;

import com.example.do_an_toeic.dto.user.ChangePasswordDto;
import com.example.do_an_toeic.dto.user.ProfileDto;
import com.example.do_an_toeic.entity.User;
import com.example.do_an_toeic.repository.UserRepository;
import com.example.do_an_toeic.service.user.UserService;
import com.example.do_an_toeic.utils.CommonUtils;
import com.example.do_an_toeic.utils.GetUser;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/redirect-password")
    public String redirectPassword(Model model) {
        model.addAttribute("changePasswordDto", new ChangePasswordDto());
        return "/user/change-password";
    }

    @PostMapping("/change-password")
    public String changePassword(@Valid @ModelAttribute("changePasswordDto") ChangePasswordDto changePasswordDto,
                                 BindingResult bindingResult,RedirectAttributes redirectAttributes, Model model) {
        if(bindingResult.hasErrors()){
            return "user/change-password";
        }
        boolean success = userService.changePassword(changePasswordDto.getOldPassword(), changePasswordDto.getNewPassword(), changePasswordDto.getConfirmPassword(), GetUser.getUserName());
        if (success) {
            redirectAttributes.addFlashAttribute("changeSuccess", "Đổi mật khẩu thành công");
            return "redirect:/admin/user/redirect-password";
        } else {
            model.addAttribute("passwordIncorrect", "Thông tin mật khẩu không chính xác");
            return "user/change-password";
        }
    }

    @GetMapping("/redirect-profile")
    public String redirectProfile(Model model){
        ProfileDto profileDto = modelMapper.map(userService.getByUser(GetUser.getUserName()),ProfileDto.class);
        if(!CommonUtils.isEmptyOrNull(profileDto.getBirthDay())){
            LocalDate birthDayDate = LocalDate.parse(profileDto.getBirthDay());
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            profileDto.setBirthDay(birthDayDate.format(outputFormatter));
        }

        model.addAttribute("profileDto",profileDto);
        return "/user/profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@Valid @ModelAttribute("profileDto") ProfileDto profileDto,
                                 BindingResult bindingResult,
                                         RedirectAttributes redirectAttributes, Model model){
        if(bindingResult.hasErrors()){
            return "/user/profile";
        }
        Optional<User> userOp = userRepository.findById(profileDto.getId());
        if(userOp.isEmpty()){
            redirectAttributes.addFlashAttribute("changeFail", "Đổi mật khẩu thành công");
            return "redirect:/admin/user/redirect-profile";
        }
        if(userRepository.findByEmailExist(profileDto.getEmail(),userOp.get().getEmail()) != null){
            model.addAttribute("emailExist","Email đã tồn tại");
            return "/user/profile";
        }
        if(userRepository.findByPhoneNumberExist(profileDto.getPhoneNumber(),userOp.get().getPhoneNumber()) != null){
            model.addAttribute("phoneNumberExist","Số điện thoại đã tồn tại");
            return "/user/profile";
        }
        User user = userOp.get();
        user.setFullName(profileDto.getFullName());
        user.setEmail(profileDto.getEmail());
        user.setPhoneNumber(profileDto.getPhoneNumber());
        user.setAddress(profileDto.getAddress());
        if(!CommonUtils.isEmptyOrNull(profileDto.getBirthDay())){
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDateBirthday = LocalDate.parse(profileDto.getBirthDay(), inputFormatter);
            Date birthDate = Date.from(localDateBirthday.atStartOfDay(ZoneId.systemDefault()).toInstant());
            user.setBirthDay(birthDate);
        }
        userRepository.save(user);
        redirectAttributes.addFlashAttribute("changeSuccess", "Cập nhập thành công");
        return "redirect:/admin/user/redirect-profile";
    }
}

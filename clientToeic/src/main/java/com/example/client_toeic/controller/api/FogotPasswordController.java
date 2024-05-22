package com.example.client_toeic.controller.api;

import com.example.client_toeic.dto.SendMailDto;
import com.example.client_toeic.entity.Role;
import com.example.client_toeic.entity.User;
import com.example.client_toeic.service.account.AccountService;
import com.example.client_toeic.service.mail.SendMailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("api/forgot-password")
public class FogotPasswordController {

    @Autowired
    private SendMailService sendMailService;

    @Autowired
    private AccountService accountService;


    @PostMapping("send-mail")
    public ResponseEntity<String> sendMailCode(@RequestBody SendMailDto sendMailDto, HttpServletRequest request){
        User user =accountService.findByEmail(sendMailDto.getEmail());
        Role role = new Role(1,"ROLE_ADMIN");
        Role role1 = new Role(2,"ROLE_MANAGER");
        if(user == null){
            throw new RuntimeException("Người dùng không tồn tại");
        }
        if(user.getRoles() == null || user.getRoles().isEmpty()
                || user.getRoles().contains(role) || user.getRoles().contains(role1)){
            throw new RuntimeException("Người dùng không hợp lệ. Vui lòng liên hệ admin");
        }

        Random rand = new Random();
        int min = 100000; // Số nhỏ nhất có 6 chữ số
        int max = 999999; // Số lớn nhất có 6 chữ số
        int otpValue = rand.nextInt(max - min + 1) + min;

        String body = "<html><body>"
                + "<h2>Xin chào,</h2>"
                + "<p>Dưới đây là mã xác nhận của bạn để lấy lại mật khẩu:</p>"
                + "<h3 style=\"color: #1E88E5;\">" + otpValue + "</h3>"
                + "<p>Vui lòng sử dụng mã này để xác nhận yêu cầu của bạn.</p>"
                + "<p>Xin cảm ơn!</p>"
                + "</body></html>";
        String subject = "Mã otp xác nhận mật khẩu";

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        Integer otp = (Integer) session.getAttribute("otp");
        if(email != null && otp != null){
            throw new RuntimeException("Bạn vui lòng kiểm tra lại mail hoặc click lại sau 10 phút");
        }
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.schedule(() -> sendMailService.sendMail(sendMailDto.getEmail(), body, subject), 5, TimeUnit.SECONDS);
        session.setAttribute("email",sendMailDto.getEmail());
        session.setAttribute("otp",otpValue);
        session.setMaxInactiveInterval(600);
        return ResponseEntity.ok("Thành công");
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody SendMailDto sendMailDto,HttpServletRequest request){
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        Integer otp = (Integer) session.getAttribute("otp");
        if(email == null || otp == null){
            throw new RuntimeException("Bạn vui lòng chọn gửi mã để nhận otp");
        }
        if(sendMailDto.getCode().equals(otp)){
            User user = accountService.findByEmail(email);
            String passwordRandom =  accountService.resetPasswordUser(user);
            String body = "<html><body>"
                    + "<h2>Xin chào,</h2>"
                    + "<p>Dưới đây là mật khẩu của bạn:</p>"
                    + "<h3 style=\"color: #1E88E5;\">" + passwordRandom + "</h3>"
                    + "<p>Vui lòng bảo mật và sử dụng mật khẩu này để đăng nhập.</p>"
                    + "<p>Sau khi đăng nhập thành công hãy thay đổi lại mật khẩu của bạn để đảm bảo an toàn.</p>"
                    + "<p>Xin cảm ơn!</p>"
                    + "</body></html>";
            String subject = "Mã otp xác nhận mật khẩu";
            ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
            scheduledExecutorService.schedule(() -> sendMailService.sendMail(user.getEmail(), body, subject), 5, TimeUnit.SECONDS);
        }else{
            throw new RuntimeException("Mã otp không hợp lệ. Vui lòng nhập lại");
        }
        return ResponseEntity.ok("Thành công");
    }

}

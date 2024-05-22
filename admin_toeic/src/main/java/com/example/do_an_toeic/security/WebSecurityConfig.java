package com.example.do_an_toeic.security;

import com.example.do_an_toeic.service.user.UserService;
import com.example.do_an_toeic.service.user.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// trang admin
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain1(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(
                        configurer -> configurer
                                .requestMatchers("/css/**","/icons/**","/images/**","/js/**","/plugins/**").permitAll()
//                        .requestMatchers("/").permitAll() // các trang web này sẽ được bỏ qua xác thực
//                        .anyRequest().authenticated() // tất cả các trang web đều sẽ bị xác thực
                                .requestMatchers("/api/question/**","/api/exercise/**").permitAll()
                                .requestMatchers("/admin/**").hasAnyRole("ADMIN","MANAGER")

                                .requestMatchers("/error/admin/show-error").permitAll()
                                .anyRequest().authenticated()
//                                .requestMatchers("/user/**","/home","/").hasAnyRole("ADMIN","MANAGER","USER")
                )
                .formLogin(   // đều hướng sang form login tự custom
                        form->form
                                .loginPage("/show-login-page")
                                .loginProcessingUrl("/admin/xac-thuc")// form html ở login submit sẽ gửi về đây để xác thực
                                .defaultSuccessUrl("/admin/home")
                                .permitAll()
                )

                .logout( // cho phép tất cả đều logout
                        logout->logout.permitAll()
                                .logoutUrl("/admin/logout")

                )

                .exceptionHandling(
                        configurer -> configurer.accessDeniedPage("/error/admin/show-error")
                ).csrf().disable();

        return http.build();

    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    // mã hóa dữ liệu t database
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(UserService userService){
        DaoAuthenticationProvider daoAuthenticationProvider =new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
        //hàm này sẽ goi hàm loadUserByUsername từ UserServiceImp do đã implement tư UserService
    }



}

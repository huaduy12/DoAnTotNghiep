package com.example.client_toeic.security;

import com.example.client_toeic.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// trang client
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                        configurer -> configurer
                                .requestMatchers("/css/**", "/fonts/**", "/img/**", "/js/**", "/lib/**", "/scss/**","/plugins/**").permitAll()
                                //.requestMatchers("/trang-chu","/**").permitAll() // các trang web này sẽ được bỏ qua xác thực
                                //  .anyRequest().authenticated() // tất cả các trang web đều sẽ bị xác thực
                                .requestMatchers("/api/**").permitAll()
                                .requestMatchers("/client/home", "/client/news/**", "/client/about","/api/**").permitAll()
                                .requestMatchers("/error/client/show-error", "/show-login-page", "/client/register/**","/client/forgot-password/**").permitAll()
                                .requestMatchers("/client/profile","/client/self-learning/**","/client/review/**").hasAnyRole("USER","OAUTH2_USER")
                                .anyRequest().authenticated()
                )
                .formLogin(   // đều hướng sang form login tự custom
                        form -> form
                                .loginPage("/show-login-page")
                                .passwordParameter("password")
                                .usernameParameter("email")
                                .loginProcessingUrl("/client/xac-thuc")// form html ở login submit sẽ gửi về đây để xác thực
                                .defaultSuccessUrl("/client/home")
                                .permitAll()
                )
                .oauth2Login(oAuth2LoginConfigurer -> oAuth2LoginConfigurer.loginPage("/show-login-page")
                        .defaultSuccessUrl("/client/home")
                        .userInfoEndpoint
                                (userInfoEndpointConfig -> userInfoEndpointConfig.userService(customOAuth2UserService)))
                .logout( // cho phép tất cả đều logout
                        logout -> logout.permitAll()
                                .logoutUrl("/logout")

                )
                .rememberMe(
                        rememberMe ->
                                rememberMe
                                        .key("uniqueAndSecret")
                                        .tokenValiditySeconds(3 * 24 * 60 * 60)

                )
                .exceptionHandling(
                        configurer -> configurer.accessDeniedPage("/error/client/show-error")
                )
                .csrf().disable()

        ;

        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // mã hóa dữ liệu t database
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(UserService userService) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        daoAuthenticationProvider.setUserDetailsService(userService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
        //hàm này sẽ goi hàm loadUserByUsername từ UserServiceImp do đã implement tư UserService
    }


}

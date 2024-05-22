package com.example.client_toeic.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsConfig extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
// Specify the allowed origin domains
        response.setHeader("Access-Control-Allow-Origin", "*");
// Specify the allowed HTTP methods
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");

// Specify the allowed headers
        response.setHeader("Access-Control-Allow-Headers", "*");
// Enable support for credentials (e.g., cookies)
        response.setHeader("Access-Control-Allow-Credentials", "true");
        filterChain.doFilter(request, response);
    }
}

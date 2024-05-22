package com.example.client_toeic.utils;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GetUser {

    public static String getUserName(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return !CommonUtils.isEmptyOrNull(username)  ? username : null;
    }

    public static List<String> getRoles(){
        List<String> roles = new ArrayList<>();
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority authority : authorities) {
            roles.add(authority.getAuthority());
        }
        return roles;
    }


}

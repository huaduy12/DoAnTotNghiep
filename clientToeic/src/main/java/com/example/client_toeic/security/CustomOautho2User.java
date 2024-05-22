package com.example.client_toeic.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class CustomOautho2User implements OAuth2User {


  //  private OAuth2User oAuth2User;
    private  Set<GrantedAuthority> authorities;
    private  Map<String, Object> attributes;

//    public CustomOautho2User(OAuth2User oAuth2User) {
//        this.oAuth2User = oAuth2User;
//    }

    public CustomOautho2User( Set<GrantedAuthority> authorities, Map<String, Object> attributes) {
        this.authorities = authorities;
        this.attributes = attributes;
    }

    @Override
    public <A> A getAttribute(String name) {
        return OAuth2User.super.getAttribute(name);
    }

//    @Override
//    public Map<String, Object> getAttributes() {
//        return oAuth2User.getAttributes();
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return oAuth2User.getAuthorities();
//    }
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    public String getUsername() {
        return (String) attributes.get("name");
    }
}

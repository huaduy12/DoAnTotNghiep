package com.example.client_toeic.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        Set<GrantedAuthority> mappedAuthorities = new HashSet<>();

        // Extract the authorities from the OAuth2User
        for (GrantedAuthority authority : oAuth2User.getAuthorities()) {
            mappedAuthorities.add(authority);

            // Add a custom authority based on the existing ones
            if (authority instanceof OidcUserAuthority) {
                mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_OAUTH2_USER"));
            } else if (authority instanceof OAuth2UserAuthority) {
                mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_OAUTH2_USER"));
            }
        }

        // Create a new user with the mapped authorities
        return new CustomOautho2User(mappedAuthorities, oAuth2User.getAttributes());
    }
}

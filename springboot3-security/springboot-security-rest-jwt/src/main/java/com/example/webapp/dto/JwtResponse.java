package com.example.webapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class JwtResponse {
    @JsonIgnore
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private List<String> authorities;

    public JwtResponse(String accessToken, Long id, String username, String email, List<String> authorities) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.authorities = authorities;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }
}

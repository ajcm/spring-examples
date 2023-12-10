package com.example.webapp.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
public class AuthGrantedAuthority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String authority;

    @ManyToOne
    @JoinColumn(name = "auth_user_detail_id")
    private AuthUserDetails authUserDetails;


    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public AuthUserDetails getAuthUserDetails() {
        return authUserDetails;
    }

    public void setAuthUserDetails(AuthUserDetails authUserDetails) {
        this.authUserDetails = authUserDetails;
    }

    //constructors
    public AuthGrantedAuthority(String authority,AuthUserDetails userDetails) {
        this.authority = authority;
        this.authUserDetails = userDetails;
    }

    public AuthGrantedAuthority(){

    }

}
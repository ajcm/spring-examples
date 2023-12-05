package com.example.webapp.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;


@Entity
@Table(name = "user-data")
public class User {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @NotNull
  //  @Size(min = 5, max = 10)
    private String username;

    @NotNull
  // @Size(min = 5, max = 10)
    private String password;

    @NotNull
  //  @Size(min = 15, max = 50)
    private String name;

    @NotNull
   // @Size(min = 15, max = 50)
    private String email;


    //Getter and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

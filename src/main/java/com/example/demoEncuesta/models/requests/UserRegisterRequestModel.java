package com.example.demoEncuesta.models.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.example.demoEncuesta.annotations.UniqueEmail;

import lombok.Data;

@Data
public class UserRegisterRequestModel {
    
    @NotEmpty
    private String username;

    @NotEmpty
    private String name;

    @NotEmpty
    @Email
    @UniqueEmail
    private String email;

    @NotEmpty
    @Size(min = 8, max = 40)
    private String password;

    @NotEmpty
    private String role;

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }
}

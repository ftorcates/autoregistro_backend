package com.example.demoEncuesta.models.requests;

//import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserLoginRequestModel {
    @NotEmpty
    private String username;

    @NotEmpty
    @Size(min = 8, max = 40)
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

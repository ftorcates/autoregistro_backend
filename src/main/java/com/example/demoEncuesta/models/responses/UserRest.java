package com.example.demoEncuesta.models.responses;

import lombok.Data;

@Data
public class UserRest {
    
    private long id;

    private String username;

    private String name;

    private String email;

    private long role_id;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public void setId(Long id) {
        this.id = id;
    }
}

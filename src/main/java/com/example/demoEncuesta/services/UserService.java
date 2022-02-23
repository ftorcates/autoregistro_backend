package com.example.demoEncuesta.services;

import com.example.demoEncuesta.entities.UserEntity;
import com.example.demoEncuesta.models.requests.UserRegisterRequestModel;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{

    public UserDetails loadUserByUsername(String email);

    public UserEntity getUser(String email);
    
    public UserEntity createUser(UserRegisterRequestModel user);
}

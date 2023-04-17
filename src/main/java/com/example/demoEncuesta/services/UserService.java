package com.example.demoEncuesta.services;

import com.example.demoEncuesta.entities.UserEntity;
import com.example.demoEncuesta.models.requests.UserRegisterRequestModel;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{

   //public UserDetails loadUserByUsername(String email);
   public UserDetails loadUserByUsername(String username);

    public UserEntity getUser(String username);
    
    public UserEntity createUser(UserRegisterRequestModel user);
}

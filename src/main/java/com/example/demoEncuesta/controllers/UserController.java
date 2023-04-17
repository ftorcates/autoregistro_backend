package com.example.demoEncuesta.controllers;

import javax.validation.Valid;

import com.example.demoEncuesta.entities.UserEntity;
import com.example.demoEncuesta.models.requests.UserRegisterRequestModel;
import com.example.demoEncuesta.models.responses.UserRest;
import com.example.demoEncuesta.services.UserService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;
    
    //PostMapping es para indicar que es un metodo POST
    //RequestBody es para indicar que recibirá un request
    //Valid para indicar que tendrá validaciones en los campos recibidos
    @PostMapping()
    public UserRest createUser(@RequestBody @Valid UserRegisterRequestModel userModel) {
        
        UserEntity user = userService.createUser(userModel);

        UserRest userRest = new UserRest();
        
        BeanUtils.copyProperties(user, userRest);

        return userRest;
    }
    
    @GetMapping
    public UserRest getUser(Authentication authentication) {
        
        UserEntity user = userService.getUser(authentication.getPrincipal().toString());

        UserRest userRest = new UserRest();
        
        BeanUtils.copyProperties(user, userRest);

        return userRest;
    }

}

package com.example.demoEncuesta.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.demoEncuesta.annotations.UniqueEmail;
import com.example.demoEncuesta.entities.UserEntity;
import com.example.demoEncuesta.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        UserEntity user = userRepository.findByEmail(value);
        if (user == null){
            return true;
        }
        return false;
    }
    
}

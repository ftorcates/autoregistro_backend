package com.example.demoEncuesta.services;

import java.util.ArrayList;

import com.example.demoEncuesta.entities.UserEntity;
import com.example.demoEncuesta.models.requests.UserRegisterRequestModel;
import com.example.demoEncuesta.repositories.UserRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder; 

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        //para poder encriptar la contraseña
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserEntity createUser(UserRegisterRequestModel user) {
        UserEntity userEntity = new UserEntity();
        
        BeanUtils.copyProperties(user, userEntity);

        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        return userRepository.save(userEntity);
    }
    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //UserEntity userEntity = userRepository.findByEmail(email);
        UserEntity userEntity = userRepository.findByUsername(username);

        if (userEntity == null){
            throw new UsernameNotFoundException(username);
        }
       // return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
       return new User(userEntity.getUsername(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }

    @Override
    public UserEntity getUser(String username) {   
        return userRepository.findByUsername(username);
    }
    
}

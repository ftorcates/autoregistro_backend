package com.example.demoEncuesta;

import java.util.Random;

import com.example.demoEncuesta.models.requests.UserRegisterRequestModel;

public class TestUtil {
    public static UserRegisterRequestModel createValidUser(){
        UserRegisterRequestModel user = new UserRegisterRequestModel();

        user.setEmail(generateRandomString(15)+ "@gmail.com");
        user.setName(generateRandomString(12));
        user.setPassword(generateRandomString(8));    

        return user;
    }

    //Genera string aleatorios
    public static String generateRandomString(int len){
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        Random rnd = new Random();

        StringBuilder sb = new StringBuilder(len);

        int leng = sb.length();

        System.out.println(sb.toString());

        for (int i = 0; i < len; i++){
            int charLen = chars.length();
            int nextI = rnd.nextInt(charLen);
            char ch = chars.charAt(nextI);
            sb.append(ch);

            System.out.println(sb.toString());
        }

        System.out.println(sb.toString());

        return sb.toString();
    }
}

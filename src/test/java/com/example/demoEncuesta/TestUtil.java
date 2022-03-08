package com.example.demoEncuesta;

import java.util.ArrayList;
import java.util.Random;

import com.example.demoEncuesta.models.requests.AnswerCreationRequestModel;
import com.example.demoEncuesta.models.requests.PollCreationRequestModel;
import com.example.demoEncuesta.models.requests.QuestionCreationRequestModel;
import com.example.demoEncuesta.models.requests.UserRegisterRequestModel;

import org.apache.tomcat.jni.Poll;

public class TestUtil {
    public static UserRegisterRequestModel createValidUser(){
        UserRegisterRequestModel user = new UserRegisterRequestModel();

        user.setEmail(generateRandomString(15)+ "@gmail.com");
        user.setName(generateRandomString(12));
        user.setPassword(generateRandomString(8));    

        return user;
    }

    //crear encuesta valida
    public static PollCreationRequestModel createValidPoll(){
        ArrayList<AnswerCreationRequestModel> answers = new ArrayList<>();
        AnswerCreationRequestModel answer = new AnswerCreationRequestModel();
        answer.setContent(generateRandomString(30));
        answers.add(answer);

        ArrayList<QuestionCreationRequestModel> questions = new ArrayList<>();
        QuestionCreationRequestModel question = new QuestionCreationRequestModel();
        question.setContent(generateRandomString(30));
        question.setQuestionOrder(1);
        question.setType("CHECKBOX");
        question.setAnswers(answers);
        questions.add(question);

        PollCreationRequestModel poll = new PollCreationRequestModel();
        poll.setContent(generateRandomString(30));
        poll.setOpened(true);
        poll.setQuestions(questions);

        return poll;
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

package com.example.demoEncuesta.services;

import java.util.UUID;

import com.example.demoEncuesta.entities.AnswerEntity;
import com.example.demoEncuesta.entities.PollEntity;
import com.example.demoEncuesta.entities.QuestionEntity;
import com.example.demoEncuesta.entities.UserEntity;
import com.example.demoEncuesta.models.requests.PollCreationRequestModel;
import com.example.demoEncuesta.repositories.PollRepository;
import com.example.demoEncuesta.repositories.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PollServiceImpl implements PollService{
    
    PollRepository pollRepository;
    UserRepository userRepository;

    public PollServiceImpl(PollRepository pollRepository, UserRepository userRepository){
        this.pollRepository = pollRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String createPoll(PollCreationRequestModel model, String email){
    
        UserEntity user = userRepository.findByEmail(email);

        ModelMapper mapper = new ModelMapper();

        //Vamos a mapear desde PollCreationRequestModel a PollEntity
        PollEntity pollEntity = mapper.map(model, PollEntity.class);

        pollEntity.setUser(user);

        pollEntity.setPollId(UUID.randomUUID().toString());

        //Recorremos las preguntas para setear manualmente las poll y las preguntas.
        for (QuestionEntity question: pollEntity.getQuestions()){
            question.setPoll(pollEntity);
            for (AnswerEntity answer: question.getAnswers()){
                answer.setQuestion(question);
            }
        }

        pollRepository.save(pollEntity);

        return pollEntity.getPollId();
    }
}

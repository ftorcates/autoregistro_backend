package com.example.demoEncuesta.controllers;

import javax.validation.Valid;

import com.example.demoEncuesta.models.requests.PollCreationRequestModel;
import com.example.demoEncuesta.models.responses.CreatedPollRest;
import com.example.demoEncuesta.services.PollService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/polls")
public class PollController {
    
    @Autowired
    PollService pollService;

    @PostMapping
    public CreatedPollRest createPoll(@RequestBody @Valid PollCreationRequestModel model, Authentication authentication) {
        String pollId = pollService.createPoll(model, authentication.getPrincipal().toString());
        return new CreatedPollRest(pollId);
    }
}

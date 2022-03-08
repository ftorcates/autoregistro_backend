package com.example.demoEncuesta.services;

import com.example.demoEncuesta.models.requests.PollCreationRequestModel;

public interface PollService {
    public String createPoll(PollCreationRequestModel model, String  email);
}

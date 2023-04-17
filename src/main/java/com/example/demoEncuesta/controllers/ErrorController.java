package com.example.demoEncuesta.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.example.demoEncuesta.entities.ErrorEntity;
import com.example.demoEncuesta.models.requests.ErrorRequestModel;
import com.example.demoEncuesta.models.responses.ErrorCreatedRest;
import com.example.demoEncuesta.models.responses.ErrorRest;
import com.example.demoEncuesta.services.ErrorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/errors")
public class ErrorController {
    
    @Autowired
    ErrorService errorService;

    @PostMapping
    public ErrorCreatedRest createError(@RequestBody @Valid ErrorRequestModel error, Authentication authentication){
        
        errorService.createError(error);

        return new ErrorCreatedRest(error.getStreamId());
    }

    @GetMapping(path = "/stream/{streamId}")
    public List<ErrorRest> getErrorsByStream(@PathVariable long streamId){
        
        List<ErrorEntity> errorsEntity = errorService.getErrorsByStreamId(streamId);

        List<ErrorRest> errors = new ArrayList<>();

        for (int i = 0; i < errorsEntity.size(); i++  ){
            ErrorRest error = new ErrorRest();

            error.setIdStream(streamId);
            error.setIdError(errorsEntity.get(i).getId());
            error.setDescriptionError(errorsEntity.get(i).getError_description());

            errors.add(error);
        }

        return errors;
    }

    @GetMapping(path = "{id}")
    public ErrorRest getErrorsById(@PathVariable long id) {

        ErrorEntity errorEntity = errorService.getErrorById(id);

        ErrorRest error = new ErrorRest();
                
        error.setIdStream(errorEntity.getStream().getId());
        error.setIdError(errorEntity.getId());
        error.setDescriptionError(errorEntity.getError_description());
        
        return error;
    }
}

package com.example.demoEncuesta.services;

import java.util.List;

import com.example.demoEncuesta.entities.ErrorEntity;
import com.example.demoEncuesta.entities.StreamEntity;
import com.example.demoEncuesta.models.requests.ErrorRequestModel;
import com.example.demoEncuesta.repositories.ErrorRepository;
import com.example.demoEncuesta.repositories.StreamRepository;

import org.springframework.stereotype.Service;

@Service
public class ErrorServiceImpl implements ErrorService{

    ErrorRepository errorRepository;
    StreamRepository streamRepository;

    public ErrorServiceImpl (ErrorRepository errorRepository, StreamRepository streamRepository){
        this.errorRepository = errorRepository;
        this.streamRepository = streamRepository;
    }

    @Override
    public ErrorEntity createError(ErrorRequestModel model) {
        
        ErrorEntity error = new ErrorEntity();

        error.setError_description(model.getErrorDescription());
        
        StreamEntity stream = streamRepository.findById(model.getStreamId());
        error.setStream(stream);
        
        return errorRepository.save(error);
    }

    @Override
    public List<ErrorEntity> getErrorsByStreamId(long streamId) {
        
        return errorRepository.findByStreamId(streamId);
    }

    @Override
    public ErrorEntity getErrorById(long id) {
        
        ErrorEntity error = errorRepository.findById(id);
        
        return error;
    }
    
}

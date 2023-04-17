package com.example.demoEncuesta.services;

import java.util.List;

import com.example.demoEncuesta.entities.ErrorEntity;
import com.example.demoEncuesta.models.requests.ErrorRequestModel;

public interface ErrorService {
    
    public ErrorEntity createError(ErrorRequestModel model);

    public List<ErrorEntity> getErrorsByStreamId(long streamId);

    public ErrorEntity getErrorById(long id);
}

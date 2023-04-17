package com.example.demoEncuesta.services;

import java.util.List;

import com.example.demoEncuesta.entities.ErrorSummaryEntity;
import com.example.demoEncuesta.models.requests.ErrorSummaryRequestModel;

public interface ErrorSummaryService {
    
    public ErrorSummaryEntity createErrorSummary(ErrorSummaryRequestModel model);

    public List<ErrorSummaryEntity> getSummaryByErrorId(long errorId);

    public ErrorSummaryEntity getErrorSummaryById(long id);
}

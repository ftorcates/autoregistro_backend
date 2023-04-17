package com.example.demoEncuesta.services;

import java.util.List;

import com.example.demoEncuesta.entities.ErrorEntity;
import com.example.demoEncuesta.entities.ErrorSummaryEntity;
import com.example.demoEncuesta.models.requests.ErrorSummaryRequestModel;
import com.example.demoEncuesta.repositories.ErrorRepository;
import com.example.demoEncuesta.repositories.ErrorSummaryRepository;

import org.springframework.stereotype.Service;

@Service
public class ErrorSummaryServiceImpl implements ErrorSummaryService{

    ErrorSummaryRepository errorSummaryRepository;
    ErrorRepository errorRepository;

    public ErrorSummaryServiceImpl(ErrorSummaryRepository errorSummaryRepository, ErrorRepository errorRepository){
        this.errorSummaryRepository = errorSummaryRepository;
        this.errorRepository = errorRepository;
    }

    @Override
    public ErrorSummaryEntity createErrorSummary(ErrorSummaryRequestModel model) {
        
        ErrorSummaryEntity summary = new ErrorSummaryEntity();

        summary.setSummary_description(model.getSummaryDescription());
        summary.setInfo_adicional(model.getInfoAdicional());

        ErrorEntity error = errorRepository.findById(model.getErrorId());
        summary.setError(error);

        return errorSummaryRepository.save(summary);
    }

    @Override
    public List<ErrorSummaryEntity> getSummaryByErrorId(long errorId) {
        
        return errorSummaryRepository.findByErrorId(errorId);
    }

    @Override
    public ErrorSummaryEntity getErrorSummaryById(long id) {
        
        ErrorSummaryEntity summary = errorSummaryRepository.findById(id);
        
        return summary;
    }
    
}

package com.example.demoEncuesta.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.example.demoEncuesta.entities.ErrorSummaryEntity;
import com.example.demoEncuesta.models.requests.ErrorSummaryRequestModel;
import com.example.demoEncuesta.models.responses.ErrorSummaryCreatedRest;
import com.example.demoEncuesta.models.responses.ErrorSummaryRest;
import com.example.demoEncuesta.services.ErrorSummaryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/errorSummaries")
public class ErrorSummaryController {

    @Autowired
    ErrorSummaryService errorSummaryService;
    
    @PostMapping
    public ErrorSummaryCreatedRest createErrorSummary(@RequestBody @Valid ErrorSummaryRequestModel summary, Authentication authentication){
        
        errorSummaryService.createErrorSummary(summary);

        return new ErrorSummaryCreatedRest(summary.getErrorId());
    }

    @GetMapping(path = "/error/{errorId}")
    public List<ErrorSummaryRest> getErrorSummariesByError(@PathVariable long errorId){

        List<ErrorSummaryEntity> summariesEntity = errorSummaryService.getSummaryByErrorId(errorId);

        List<ErrorSummaryRest> summaries = new ArrayList<>();

        for (int i = 0; i < summariesEntity.size(); i++){
            ErrorSummaryRest summary = new ErrorSummaryRest();

            summary.setIdError(errorId);
            summary.setIdErrorSummary(summariesEntity.get(i).getId());
            summary.setDescriptionSummary(summariesEntity.get(i).getSummary_description());
            summary.setInfoAdicional(summariesEntity.get(i).getInfo_adicional());

            summaries.add(summary);

        }
        
        return summaries;
    }

    @GetMapping(path = "{id}")
    public ErrorSummaryRest getErrorsById(@PathVariable long id) {

        ErrorSummaryEntity summaryEntity = errorSummaryService.getErrorSummaryById(id);

        ErrorSummaryRest summary = new ErrorSummaryRest();
                
        summary.setIdError(summaryEntity.getError().getId());
        summary.setIdErrorSummary(summaryEntity.getId());
        summary.setDescriptionSummary(summaryEntity.getSummary_description());
        summary.setInfoAdicional(summaryEntity.getInfo_adicional());
        
        return summary;
    }
}

package com.example.demoEncuesta.models.requests;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class ErrorSummaryRequestModel {
    
    
    private long errorId;

    @NotEmpty
    private String summaryDescription;

    private String infoAdicional;
}

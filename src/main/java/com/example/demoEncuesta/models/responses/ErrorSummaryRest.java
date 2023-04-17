package com.example.demoEncuesta.models.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorSummaryRest {

    private long idError;

    private long idErrorSummary;

    private String descriptionSummary;

    private String infoAdicional;

    public ErrorSummaryRest(){}
    
}

package com.example.demoEncuesta.models.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorRest {
    
    private long idStream;

    private long idError;

    private String descriptionError;

    public ErrorRest(){}
}

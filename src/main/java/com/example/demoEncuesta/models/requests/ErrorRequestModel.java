package com.example.demoEncuesta.models.requests;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ErrorRequestModel {
    
    @NotNull
    private long streamId;

    @NotNull
    private String errorDescription;
}

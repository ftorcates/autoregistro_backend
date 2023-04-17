package com.example.demoEncuesta.models.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StreamRest {
    
    private long idStream;

    private String descriptionStream;

    public StreamRest(){}
}

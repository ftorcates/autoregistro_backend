package com.example.demoEncuesta.models.responses;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {
    
    private String errors;

    private Date timestamp;

    private int status;

}

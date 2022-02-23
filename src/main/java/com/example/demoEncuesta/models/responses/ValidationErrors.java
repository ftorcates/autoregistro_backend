package com.example.demoEncuesta.models.responses;

import java.util.Date;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationErrors {

    private Map<String, String> errors;

    private Date timestamp;
/*
    public ValidationErrors(Map<String, String> e, Date t){
        errors = e;
        timestamp = t;
    };*/

    public Map<String, String> getErrors() {
        return errors;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    
}

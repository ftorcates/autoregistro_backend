package com.example.demoEncuesta.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.example.demoEncuesta.models.responses.ErrorMessage;
import com.example.demoEncuesta.models.responses.ValidationErrors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class AppExceptionHandler {
    
    //Atrapa las excepciones de validacion de datos (BAD_REQUEST)
    @ExceptionHandler(value = { MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleValidationErrorException(MethodArgumentNotValidException ex, WebRequest webRequest){

        Map<String, String> errors = new HashMap<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()){
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        }

        ValidationErrors validationErrors = new ValidationErrors(errors, new Date());

        return new ResponseEntity<>(validationErrors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    //Atrapa las excepciones que no sean de validación (INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<Object> handleException(Exception ex, WebRequest webRequest){
        
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), new Date(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

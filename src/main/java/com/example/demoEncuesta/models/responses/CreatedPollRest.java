package com.example.demoEncuesta.models.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
//Para generar un constructor que acepte todos los datos
@AllArgsConstructor
public class CreatedPollRest {
    private String pollId;
}

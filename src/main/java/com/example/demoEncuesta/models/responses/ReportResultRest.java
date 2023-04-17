package com.example.demoEncuesta.models.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportResultRest {
    
    private long id;

    private String responsable;

    private String fecha_soporte;

    private String turno;

    private String nivel;

    private String pais;

    private String complejidad;

    private String categoria;

    private String stream;

    private String err;

    private String summary;

    private String tienda;

    private String descripcion;

    private String analisis;

    private String acciones;

    private String orden;

    private String imei;

    private String sku;

    private String usuario;

}

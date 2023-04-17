package com.example.demoEncuesta.models.responses;

import java.util.Date;

import lombok.Data;

@Data
public class ReportRest {
    
    private long id;

    private String turno;

    private String tipoSoporte;

    private String paisSoporte;

    private String responsableSoporte;

    private String categoriaSoporte;

    private String complejidadSoporte;

    private String descripcionSoporte;

    private String analisisSoporte;

    private String accionesSoporte;

    private String streamDescription;

    private String errorDescription;

    private String errorSummaryDescription;

    private String codigoTienda;

    private Date fechaSoporte;

    private String ordenAsociada;

    private String imeiAsociado;

    private String skuAsociado;

    private String usuarioAsociado;
}

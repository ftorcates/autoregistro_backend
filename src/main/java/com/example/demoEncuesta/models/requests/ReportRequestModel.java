package com.example.demoEncuesta.models.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ReportRequestModel {
    
    @NotEmpty
    private String turno;

    @NotEmpty
    private String tipoSoporte;

    @NotEmpty
    private String paisSoporte;

    private String responsableSoporte;

    @NotEmpty
    private String categoriaSoporte;

    @NotEmpty
    private String complejidadSoporte;

    @NotEmpty
    private String descripcionSoporte;

    @NotEmpty
    private String analisisSoporte;

    @NotEmpty
    private String accionesSoporte;

    @NotNull
    private long streamId;

    @NotNull
    private long errorId;

    @NotNull
    private long errorSummaryId;

    @NotEmpty
    private String codigoTienda;

    private String ordenAsociada;

    private String imeiAsociado;

    private String skuAsociado;

    private String usuarioAsociado;
}

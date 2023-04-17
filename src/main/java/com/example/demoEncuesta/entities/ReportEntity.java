package com.example.demoEncuesta.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@EntityListeners(AuditingEntityListener.class)
@Entity(name="reports")
@Data
public class ReportEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String turno;

    @Column(nullable = false)
    private String tipoSoporte;

    @Column(nullable = false)
    private String paisSoporte;

    @Column(nullable = false)
    private String responsableSoporte;

    @Column(nullable = false)
    private String categoriaSoporte;

    @Column(nullable = false)
    private String complejidadSoporte;

    @Column(nullable = false)
    private String descripcionSoporte;

    @Column(nullable = false)
    private String analisisSoporte;

    @Column(nullable = false)
    private String accionesSoporte;

    @Column
    private String ordenAsociada;

    @Column 
    private String imeiAsociado;

    @Column 
    private String skuAsociado;

    @Column 
    private String usuarioAsociado;

    @ManyToOne
    @JoinColumn(name = "stream_id")
    private StreamEntity stream;

    @ManyToOne
    @JoinColumn(name = "error_id")
    private ErrorEntity error;

    @ManyToOne
    @JoinColumn(name = "error_summary_id")
    private ErrorSummaryEntity errorSummary;

    @Column(nullable = false)
    private String codigoTienda;

    @CreatedDate
    private Date fechaSoporte;
}

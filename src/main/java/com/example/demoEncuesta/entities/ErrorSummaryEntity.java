package com.example.demoEncuesta.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity(name="errorSummary")
@Data
public class ErrorSummaryEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String summary_description;

    @Column 
    private String info_adicional;

    @ManyToOne
    @JoinColumn(name = "error_id")
    private ErrorEntity error;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "errorSummary")
    private List<ReportEntity> reports = new ArrayList<>();
    
}

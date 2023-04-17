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

@Entity(name="errors")
@Data
public class ErrorEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String error_description; 

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "error")
    private List<ErrorSummaryEntity> summaries = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "stream_id")
    private StreamEntity stream;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "error")
    private List<ReportEntity> reports = new ArrayList<>();
    
}

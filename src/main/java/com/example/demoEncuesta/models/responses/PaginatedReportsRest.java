package com.example.demoEncuesta.models.responses;

import java.util.List;

import lombok.Data;

@Data
public class PaginatedReportsRest {
    
    private List<ReportRest> reports;

    //total de paginas
    private int totalPages;

    //total de registros
    private long totalRecords;

    //numero de registros en la pagina actual
    private long currentPageRecords;

    //pagina actual
    private int currentPage;
}

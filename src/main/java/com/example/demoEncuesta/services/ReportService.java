package com.example.demoEncuesta.services;

import java.util.List;

import com.example.demoEncuesta.entities.ReportEntity;
import com.example.demoEncuesta.interfaces.ReportResult;
import com.example.demoEncuesta.models.requests.ReportRequestModel;

import org.springframework.data.domain.Page;

public interface ReportService {
    
    public ReportEntity createReport(ReportRequestModel model);

    //page es el nro de pagina, limit es el limite por pagina
    public Page<ReportEntity> getReportsByUsername(int page, int limit, String email);

    public List<ReportResult> getMyReports(String username);

    //page es el nro de pagina, limit es el limite por pagina
    public Page<ReportEntity> getAllReports(int page, int limit);

    public List<ReportResult> getLastReports();

    public void deleteReport(long reportId, String username);

    public ReportEntity getReportById(long id);

    public List<ReportResult> getReportsByStreamAndDate(long streamId, long antiguedad);

    public List<ReportResult> getReportsByErrorAndDate(long streamId, long errorId, long antiguedad);

    public List<ReportResult> getReportsBySummaryAndDate(long streamId, long errorId, long summaryId, long antiguedad);

    public List<ReportResult> getReportsByTiendaAndDate(String codigoTienda, long antiguedad);

    public List<ReportResult> getReportsByOrderAndDate(String orderId, long antiguedad);

    public List<ReportResult> getReportsByImeiAndDate(String imei, long antiguedad);

    public List<ReportResult> getReportsBySkuAndDate(String sku, long antiguedad);
}

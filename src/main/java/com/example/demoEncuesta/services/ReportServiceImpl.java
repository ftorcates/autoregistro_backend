package com.example.demoEncuesta.services;

import java.util.List;

import com.example.demoEncuesta.entities.ErrorEntity;
import com.example.demoEncuesta.entities.ErrorSummaryEntity;
import com.example.demoEncuesta.entities.ReportEntity;
import com.example.demoEncuesta.entities.StreamEntity;
import com.example.demoEncuesta.interfaces.ReportResult;
import com.example.demoEncuesta.models.requests.ReportRequestModel;
import com.example.demoEncuesta.repositories.ErrorRepository;
import com.example.demoEncuesta.repositories.ErrorSummaryRepository;
import com.example.demoEncuesta.repositories.ReportRepository;
import com.example.demoEncuesta.repositories.StreamRepository;
import com.example.demoEncuesta.repositories.UserRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {

    UserRepository userRepository;
    ReportRepository reportRepository;
    StreamRepository streamRepository;
    ErrorRepository errorRepository;
    ErrorSummaryRepository errorSummaryRepository;

    public ReportServiceImpl(ReportRepository reportRepository, StreamRepository streamRepository, ErrorRepository errorRepository, ErrorSummaryRepository errorSummaryRepository, UserRepository userRepository){
        this.reportRepository = reportRepository;
        this.streamRepository = streamRepository;
        this.errorRepository = errorRepository;
        this.errorSummaryRepository = errorSummaryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ReportEntity createReport(ReportRequestModel model) {
        
        ReportEntity report = new ReportEntity();

        BeanUtils.copyProperties(model, report);

        StreamEntity stream = streamRepository.findById(model.getStreamId());
        report.setStream(stream);

        ErrorEntity error = errorRepository.findById(model.getErrorId());
        report.setError(error);

        ErrorSummaryEntity errorSummary = errorSummaryRepository.findById(model.getErrorSummaryId());
        report.setErrorSummary(errorSummary);

        return reportRepository.save(report);
    }

    @Override
    public Page<ReportEntity> getReportsByUsername(int page, int limit, String username) {
        //con esto le decimos cómo queremos el paginado
        Pageable pageable = PageRequest.of(page, limit);

        Page<ReportEntity> paginatedReports = this.reportRepository.findAllByResponsableSoporte(username, pageable);

        return paginatedReports;
    }

    @Override
    public Page<ReportEntity> getAllReports(int page, int limit) {
        //con esto le decimos cómo queremos el paginado
        Pageable pageable = PageRequest.of(page, limit);

        Page<ReportEntity> paginatedReports = this.reportRepository.findAll(pageable);

        return paginatedReports;
    }

    @Override
    public void deleteReport(long reportId, String username) {
        //Buscamos la poll con el id y el userId
        ReportEntity report = reportRepository.findByIdAndResponsableSoporte(reportId, username);

        //Si no existe la poll o no pertenece a ese usuario
        if (report == null){
            throw new RuntimeException("El reporte no existe o no está asignado a su usuario");
        }

        //borramos la encuesta
        reportRepository.delete(report);
    }

    @Override
    public ReportEntity getReportById(long id) {
        
        ReportEntity report = reportRepository.findById(id);

        return report;
    }

    @Override
    public List<ReportResult> getLastReports() {
        
        List<ReportResult> reports = this.reportRepository.getLastReportsResults();

        return reports;
    }

    @Override
    public List<ReportResult> getMyReports(String username) {
        
        List<ReportResult> reports = this.reportRepository.getMyReports(username);

        return reports;
    }

    @Override
    public List<ReportResult> getReportsByStreamAndDate(long streamId, long antiguedad) {
        
        List<ReportResult> reports = this.reportRepository.getReportsByStreamIdAndDate(streamId, antiguedad);
        return reports;
    }

    @Override
    public List<ReportResult> getReportsByErrorAndDate(long streamId, long errorId, long antiguedad) {
        
        List<ReportResult> reports = this.reportRepository.getReportsByErrorIdAndDate(streamId, errorId, antiguedad);
        return reports;
    }

    @Override
    public List<ReportResult> getReportsBySummaryAndDate(long streamId, long errorId, long summaryId, long antiguedad) {
        
        List<ReportResult> reports = this.reportRepository.getReportsBySummaryIdAndDate(streamId, errorId, summaryId, antiguedad);
        return reports;
    }

    @Override
    public List<ReportResult> getReportsByTiendaAndDate(String codigoTienda, long antiguedad) {
        
        List<ReportResult> reports = this.reportRepository.getReportsByTiendaAndDate(codigoTienda, antiguedad);
        return reports;
    }
    

    @Override
    public List<ReportResult> getReportsByOrderAndDate(String orderId, long antiguedad) {
        
        List<ReportResult> reports = this.reportRepository.getReportsByOrderAndDate(orderId, antiguedad);
        return reports;
    }

    @Override
    public List<ReportResult> getReportsByImeiAndDate(String imei, long antiguedad) {
        
        List<ReportResult> reports = this.reportRepository.getReportsByImeiAndDate(imei, antiguedad);
        return reports;
    }

    @Override
    public List<ReportResult> getReportsBySkuAndDate(String sku, long antiguedad) {
        
        List<ReportResult> reports = this.reportRepository.getReportsBySkuAndDate(sku, antiguedad);
        return reports;
    }
    
}

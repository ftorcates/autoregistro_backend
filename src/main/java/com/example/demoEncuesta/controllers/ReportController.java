package com.example.demoEncuesta.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.example.demoEncuesta.entities.ReportEntity;
import com.example.demoEncuesta.interfaces.ReportResult;
import com.example.demoEncuesta.models.requests.ReportRequestModel;
import com.example.demoEncuesta.models.responses.PaginatedReportsRest;
import com.example.demoEncuesta.models.responses.ReportCreatedRest;
import com.example.demoEncuesta.models.responses.ReportRest;
import com.example.demoEncuesta.services.ReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/reports")
public class ReportController {
    
    @Autowired
    ReportService reportService;

    @PostMapping
    public ReportCreatedRest createReport(@RequestBody @Valid ReportRequestModel model, Authentication authentication){
        
        model.setResponsableSoporte(authentication.getPrincipal().toString());
        ReportEntity report = reportService.createReport(model);
        
        return new ReportCreatedRest(report.getId());
    }

    @GetMapping(path = "{id}")
    public ReportRest getReportById(@PathVariable long id){

        ReportEntity entity = reportService.getReportById(id);

        ReportRest report = new ReportRest();

        report.setAccionesSoporte(entity.getAccionesSoporte());
        report.setAnalisisSoporte(entity.getAnalisisSoporte());
        report.setCategoriaSoporte(entity.getCategoriaSoporte());
        report.setCodigoTienda(entity.getCodigoTienda());
        report.setComplejidadSoporte(entity.getComplejidadSoporte());
        report.setDescripcionSoporte(entity.getDescripcionSoporte());
        report.setErrorDescription(entity.getError().getError_description());
        report.setErrorSummaryDescription(entity.getErrorSummary().getSummary_description());
        report.setFechaSoporte(entity.getFechaSoporte());
        report.setId(entity.getId());
        report.setImeiAsociado(entity.getImeiAsociado());
        report.setOrdenAsociada(entity.getOrdenAsociada());
        report.setPaisSoporte(entity.getPaisSoporte());
        report.setResponsableSoporte(entity.getResponsableSoporte());
        report.setSkuAsociado(entity.getSkuAsociado());
        report.setStreamDescription(entity.getStream().getDescription());
        report.setTipoSoporte(entity.getTipoSoporte());
        report.setTurno(entity.getTurno());
        report.setUsuarioAsociado(entity.getUsuarioAsociado());

        return report;
    }

    @GetMapping
    public PaginatedReportsRest getReports(@RequestParam(value="page", defaultValue="0") int page, 
                         @RequestParam(value="limit", defaultValue="10") int limit,
                         Authentication authentication){

        //la pagina y el limite vendran del url del endpoint /polls?page=X&liimit=Y
        Page<ReportEntity> paginatedReports = reportService.getReportsByUsername(page, limit, authentication.getPrincipal().toString());
        
        //Configuremos el mapeo para que no mapee la lista de Questions de Entity a Rest
        //mapper.typeMap(PollEntity.class, PollRest.class).addMappings(m -> m.skip(PollRest::setQuestions));

        PaginatedReportsRest paginatedReportsRest = new PaginatedReportsRest();

        System.out.println("Size "+paginatedReports.getContent().size());

        List<ReportRest> reports = new ArrayList<>();
        

        for (int i=0; i<paginatedReports.getContent().size(); i++){
            ReportRest report = new ReportRest();
            System.out.println("i "+i);
            report.setId(paginatedReports.getContent().get(i).getId());
            report.setTurno(paginatedReports.getContent().get(i).getTurno());
            report.setTipoSoporte(paginatedReports.getContent().get(i).getTipoSoporte());
            report.setPaisSoporte(paginatedReports.getContent().get(i).getPaisSoporte());
            report.setResponsableSoporte(paginatedReports.getContent().get(i).getResponsableSoporte());
            report.setCategoriaSoporte(paginatedReports.getContent().get(i).getCategoriaSoporte());
            report.setComplejidadSoporte(paginatedReports.getContent().get(i).getComplejidadSoporte());
            report.setDescripcionSoporte(paginatedReports.getContent().get(i).getDescripcionSoporte());
            report.setAnalisisSoporte(paginatedReports.getContent().get(i).getAnalisisSoporte());
            report.setAccionesSoporte(paginatedReports.getContent().get(i).getAccionesSoporte());
            report.setCodigoTienda(paginatedReports.getContent().get(i).getCodigoTienda());
            report.setFechaSoporte(paginatedReports.getContent().get(i).getFechaSoporte());
            report.setStreamDescription(paginatedReports.getContent().get(i).getStream().getDescription());
            report.setErrorDescription(paginatedReports.getContent().get(i).getError().getError_description());
            report.setErrorSummaryDescription(paginatedReports.getContent().get(i).getErrorSummary().getSummary_description());
            report.setOrdenAsociada(paginatedReports.getContent().get(i).getOrdenAsociada());
            report.setSkuAsociado(paginatedReports.getContent().get(i).getSkuAsociado());
            report.setImeiAsociado(paginatedReports.getContent().get(i).getImeiAsociado());
            report.setUsuarioAsociado(paginatedReports.getContent().get(i).getUsuarioAsociado());

            System.out.println("Descripcion: "+report.getDescripcionSoporte());
            System.out.println("Stream: "+report.getStreamDescription());
            System.out.println("Error: "+report.getErrorDescription());
            System.out.println("Summary: "+report.getErrorSummaryDescription());
            reports.add(report);
        }

        paginatedReportsRest.setReports(reports);

        /*paginatedReportsRest.setReports(
            paginatedReports.getContent().stream().map(p -> mapper.map(p, ReportRest.class)).collect(Collectors.toList())
        );*/

        /*System.out.println(paginatedReportsRest);
        for (int i = 0; i < paginatedReportsRest.getReports().size(); i++){
            paginatedReportsRest.getReports().get(i).setQuestions(null);
        }
        System.out.println(paginatedPollsRest);*/
        paginatedReportsRest.setTotalPages(paginatedReports.getTotalPages());
        paginatedReportsRest.setTotalRecords(paginatedReports.getTotalElements());
        paginatedReportsRest.setCurrentPageRecords(paginatedReports.getNumberOfElements());
        paginatedReportsRest.setCurrentPage(paginatedReports.getPageable().getPageNumber() + 1);

        return paginatedReportsRest;
    }

    

    @GetMapping(path = "/all")
    public PaginatedReportsRest getAllReports(@RequestParam(value="page", defaultValue="0") int page, 
                         @RequestParam(value="limit", defaultValue="10") int limit,
                         Authentication authentication){

        //la pagina y el limite vendran del url del endpoint /polls?page=X&liimit=Y
        Page<ReportEntity> paginatedReports = reportService.getAllReports(page, limit);
      
        PaginatedReportsRest paginatedReportsRest = new PaginatedReportsRest();

        List<ReportRest> reports = new ArrayList<>();        

        for (int i=0; i<paginatedReports.getContent().size(); i++){
            ReportRest report = new ReportRest();
            System.out.println("i "+i);
            report.setId(paginatedReports.getContent().get(i).getId());
            report.setTurno(paginatedReports.getContent().get(i).getTurno());
            report.setTipoSoporte(paginatedReports.getContent().get(i).getTipoSoporte());
            report.setPaisSoporte(paginatedReports.getContent().get(i).getPaisSoporte());
            report.setResponsableSoporte(paginatedReports.getContent().get(i).getResponsableSoporte());
            report.setCategoriaSoporte(paginatedReports.getContent().get(i).getCategoriaSoporte());
            report.setComplejidadSoporte(paginatedReports.getContent().get(i).getComplejidadSoporte());
            report.setDescripcionSoporte(paginatedReports.getContent().get(i).getDescripcionSoporte());
            report.setAnalisisSoporte(paginatedReports.getContent().get(i).getAnalisisSoporte());
            report.setAccionesSoporte(paginatedReports.getContent().get(i).getAccionesSoporte());
            report.setCodigoTienda(paginatedReports.getContent().get(i).getCodigoTienda());
            report.setFechaSoporte(paginatedReports.getContent().get(i).getFechaSoporte());
            report.setStreamDescription(paginatedReports.getContent().get(i).getStream().getDescription());
            report.setErrorDescription(paginatedReports.getContent().get(i).getError().getError_description());
            report.setErrorSummaryDescription(paginatedReports.getContent().get(i).getErrorSummary().getSummary_description());
            report.setOrdenAsociada(paginatedReports.getContent().get(i).getOrdenAsociada());
            report.setSkuAsociado(paginatedReports.getContent().get(i).getSkuAsociado());
            report.setImeiAsociado(paginatedReports.getContent().get(i).getImeiAsociado());
            report.setUsuarioAsociado(paginatedReports.getContent().get(i).getUsuarioAsociado());

            reports.add(report);
        }

        paginatedReportsRest.setReports(reports);

        paginatedReportsRest.setTotalPages(paginatedReports.getTotalPages());
        paginatedReportsRest.setTotalRecords(paginatedReports.getTotalElements());
        paginatedReportsRest.setCurrentPageRecords(paginatedReports.getNumberOfElements());
        paginatedReportsRest.setCurrentPage(paginatedReports.getPageable().getPageNumber() + 1);

        return paginatedReportsRest;
    }

    @GetMapping(path = "/lastReports")
    public List<ReportResult> getLastReports(Authentication authentication){

        List<ReportResult> reports = reportService.getLastReports();
      
        return reports;
    }

    @GetMapping(path = "/myReports")
    public List<ReportResult> getMyReports(Authentication authentication){

        List<ReportResult> reports = reportService.getMyReports(authentication.getPrincipal().toString());
      
        return reports;
    }

    @GetMapping(path = "/byStreamAndDate")
    public List<ReportResult> getReportsByStreamAndDate(@RequestParam(value="streamId", defaultValue="1") long streamId,
                                                        @RequestParam(value="antiguedad", defaultValue="1") long antiguedad, 
                                                        Authentication authentication){

        List<ReportResult> reports = reportService.getReportsByStreamAndDate(streamId, antiguedad);
      
        return reports;
    }

    @GetMapping(path = "/byErrorAndDate")
    public List<ReportResult> getReportsByErrorAndDate(@RequestParam(value="streamId", defaultValue="1") long streamId,
                                                        @RequestParam(value="errorId", defaultValue="1") long errorId,
                                                        @RequestParam(value="antiguedad", defaultValue="1") long antiguedad, 
                                                        Authentication authentication){

        List<ReportResult> reports = reportService.getReportsByErrorAndDate(streamId, errorId, antiguedad);
      
        return reports;
    }

    @GetMapping(path = "/bySummaryAndDate")
    public List<ReportResult> getReportsBySummaryAndDate(@RequestParam(value="streamId", defaultValue="1") long streamId,
                                                        @RequestParam(value="errorId", defaultValue="1") long errorId,
                                                        @RequestParam(value="summaryId", defaultValue="1") long summaryId,
                                                        @RequestParam(value="antiguedad", defaultValue="1") long antiguedad, 
                                                        Authentication authentication){

        List<ReportResult> reports = reportService.getReportsBySummaryAndDate(streamId, errorId, summaryId, antiguedad);
      
        return reports;
    }

    @GetMapping(path = "/byTiendaAndDate")
    public List<ReportResult> getReportsByTiendaAndDate(@RequestParam(value="codigoTienda", defaultValue="1") String codigoTienda,
                                                        @RequestParam(value="antiguedad", defaultValue="1") long antiguedad, 
                                                        Authentication authentication){

        List<ReportResult> reports = reportService.getReportsByTiendaAndDate(codigoTienda, antiguedad);
      
        return reports;
    }

    @GetMapping(path = "/byOrderAndDate")
    public List<ReportResult> getReportsByOrderAndDate(@RequestParam(value="orderId", defaultValue="1") String orderId,
                                                        @RequestParam(value="antiguedad", defaultValue="1") long antiguedad, 
                                                        Authentication authentication){

        List<ReportResult> reports = reportService.getReportsByOrderAndDate(orderId, antiguedad);
      
        return reports;
    }

    @GetMapping(path = "/byImeiAndDate")
    public List<ReportResult> getReportsByImeiAndDate(@RequestParam(value="imei", defaultValue="1") String imei,
                                                        @RequestParam(value="antiguedad", defaultValue="1") long antiguedad, 
                                                        Authentication authentication){

        List<ReportResult> reports = reportService.getReportsByImeiAndDate(imei, antiguedad);
      
        return reports;
    }

    @GetMapping(path = "/bySkuAndDate")
    public List<ReportResult> getReportsBySkuAndDate(@RequestParam(value="sku", defaultValue="1") String sku,
                                                        @RequestParam(value="antiguedad", defaultValue="1") long antiguedad, 
                                                        Authentication authentication){

        List<ReportResult> reports = reportService.getReportsBySkuAndDate(sku, antiguedad);
      
        return reports;
    }

    @DeleteMapping(path="/{id}")
    public void deleteReport(@PathVariable long id, Authentication authentication){
        reportService.deleteReport(id, authentication.getPrincipal().toString());
    }
}

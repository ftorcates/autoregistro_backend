package com.example.demoEncuesta.repositories;

import java.util.List;

import com.example.demoEncuesta.entities.ReportEntity;
import com.example.demoEncuesta.interfaces.ReportResult;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends CrudRepository<ReportEntity, Long> {
    
    public ReportEntity findById(long id);

    public Page<ReportEntity> findAllByResponsableSoporte(String responsable, Pageable pageable);

    public Page<ReportEntity> findAll(Pageable pageable);

    public ReportEntity findByIdAndResponsableSoporte(long id, String username);

    @Query(value = "select r.id, u.name responsable, r.fecha_soporte, r.turno turno, r.tipo_soporte nivel, r.pais_soporte pais, r.complejidad_soporte complejidad, r.categoria_soporte categoria, s.description stream, e.error_description err, es.summary_description summary, r.codigo_tienda tienda, r.descripcion_soporte descripcion, r.analisis_soporte analisis, r.acciones_soporte acciones, r.orden_asociada orden, r.imei_asociado imei, r.sku_asociado sku, r.usuario_asociado usuario from reports r, users u, streams s, errors e, error_summary es where r.responsable_soporte = u.username and r.stream_id = s.id and r.error_id = e.id and r.error_summary_id = es.id and r.fecha_soporte > DATE_SUB(NOW(),INTERVAL 48 HOUR) ORDER BY r.fecha_soporte DESC", nativeQuery = true)
    public List<ReportResult> getLastReportsResults();

    @Query(value = "select r.id, u.name responsable, r.fecha_soporte, r.turno turno, r.tipo_soporte nivel, r.pais_soporte pais, r.complejidad_soporte complejidad, r.categoria_soporte categoria, s.description stream, e.error_description err, es.summary_description summary, r.codigo_tienda tienda, r.descripcion_soporte descripcion, r.analisis_soporte analisis, r.acciones_soporte acciones, r.orden_asociada orden, r.imei_asociado imei, r.sku_asociado sku, r.usuario_asociado usuario from reports r, users u, streams s, errors e, error_summary es where r.responsable_soporte = u.username and r.stream_id = s.id and r.error_id = e.id and r.error_summary_id = es.id and r.responsable_soporte = :responsable and r.fecha_soporte > DATE_SUB(NOW(),INTERVAL 720 HOUR) ORDER BY r.fecha_soporte DESC", nativeQuery = true)
    public List<ReportResult> getMyReports(@Param("responsable") String responsable);

    @Query(value = "select r.id, u.name responsable, r.fecha_soporte, r.turno turno, r.tipo_soporte nivel, r.pais_soporte pais, r.complejidad_soporte complejidad, r.categoria_soporte categoria, s.description stream, e.error_description err, es.summary_description summary, r.codigo_tienda tienda, r.descripcion_soporte descripcion, r.analisis_soporte analisis, r.acciones_soporte acciones, r.orden_asociada orden, r.imei_asociado imei, r.sku_asociado sku, r.usuario_asociado usuario from reports r, users u, streams s, errors e, error_summary es where r.responsable_soporte = u.username and r.stream_id = s.id and r.error_id = e.id and r.error_summary_id = es.id and r.stream_id = :streamId and r.fecha_soporte > DATE_SUB(NOW(),INTERVAL :antiguedad DAY) ORDER BY r.fecha_soporte DESC", nativeQuery = true)
    public List<ReportResult> getReportsByStreamIdAndDate(@Param("streamId") long streamId, 
                                                          @Param("antiguedad") long antiguedad);

    @Query(value = "select r.id, u.name responsable, r.fecha_soporte, r.turno turno, r.tipo_soporte nivel, r.pais_soporte pais, r.complejidad_soporte complejidad, r.categoria_soporte categoria, s.description stream, e.error_description err, es.summary_description summary, r.codigo_tienda tienda, r.descripcion_soporte descripcion, r.analisis_soporte analisis, r.acciones_soporte acciones, r.orden_asociada orden, r.imei_asociado imei, r.sku_asociado sku, r.usuario_asociado usuario from reports r, users u, streams s, errors e, error_summary es where r.responsable_soporte = u.username and r.stream_id = s.id and r.error_id = e.id and r.error_summary_id = es.id and r.stream_id = :streamId and r.error_id = :errorId and r.fecha_soporte > DATE_SUB(NOW(),INTERVAL :antiguedad DAY) ORDER BY r.fecha_soporte DESC", nativeQuery = true)
    public List<ReportResult> getReportsByErrorIdAndDate(@Param("streamId") long streamId, 
                                                         @Param("errorId") long errorId,
                                                         @Param("antiguedad") long antiguedad);

    @Query(value = "select r.id, u.name responsable, r.fecha_soporte, r.turno turno, r.tipo_soporte nivel, r.pais_soporte pais, r.complejidad_soporte complejidad, r.categoria_soporte categoria, s.description stream, e.error_description err, es.summary_description summary, r.codigo_tienda tienda, r.descripcion_soporte descripcion, r.analisis_soporte analisis, r.acciones_soporte acciones, r.orden_asociada orden, r.imei_asociado imei, r.sku_asociado sku, r.usuario_asociado usuario from reports r, users u, streams s, errors e, error_summary es where r.responsable_soporte = u.username and r.stream_id = s.id and r.error_id = e.id and r.error_summary_id = es.id and r.stream_id = :streamId and r.error_id = :errorId and r.error_summary_id = :summaryId and r.fecha_soporte > DATE_SUB(NOW(),INTERVAL :antiguedad DAY) ORDER BY r.fecha_soporte DESC", nativeQuery = true)
    public List<ReportResult> getReportsBySummaryIdAndDate(@Param("streamId") long streamId, 
                                                           @Param("errorId") long errorId,
                                                           @Param("summaryId") long summaryId,
                                                           @Param("antiguedad") long antiguedad);

    @Query(value = "select r.id, u.name responsable, r.fecha_soporte, r.turno turno, r.tipo_soporte nivel, r.pais_soporte pais, r.complejidad_soporte complejidad, r.categoria_soporte categoria, s.description stream, e.error_description err, es.summary_description summary, r.codigo_tienda tienda, r.descripcion_soporte descripcion, r.analisis_soporte analisis, r.acciones_soporte acciones, r.orden_asociada orden, r.imei_asociado imei, r.sku_asociado sku, r.usuario_asociado usuario from reports r, users u, streams s, errors e, error_summary es where r.responsable_soporte = u.username and r.stream_id = s.id and r.error_id = e.id and r.error_summary_id = es.id and r.codigo_tienda = :codigoTienda and r.fecha_soporte > DATE_SUB(NOW(),INTERVAL :antiguedad DAY) ORDER BY r.fecha_soporte DESC", nativeQuery = true)
    public List<ReportResult> getReportsByTiendaAndDate(@Param("codigoTienda") String codigoTienda, 
                                                        @Param("antiguedad") long antiguedad);

    @Query(value = "select r.id, u.name responsable, r.fecha_soporte, r.turno turno, r.tipo_soporte nivel, r.pais_soporte pais, r.complejidad_soporte complejidad, r.categoria_soporte categoria, s.description stream, e.error_description err, es.summary_description summary, r.codigo_tienda tienda, r.descripcion_soporte descripcion, r.analisis_soporte analisis, r.acciones_soporte acciones, r.orden_asociada orden, r.imei_asociado imei, r.sku_asociado sku, r.usuario_asociado usuario from reports r, users u, streams s, errors e, error_summary es where r.responsable_soporte = u.username and r.stream_id = s.id and r.error_id = e.id and r.error_summary_id = es.id and r.orden_asociada = :orderId and r.fecha_soporte > DATE_SUB(NOW(),INTERVAL :antiguedad DAY) ORDER BY r.fecha_soporte DESC", nativeQuery = true)
    public List<ReportResult> getReportsByOrderAndDate(@Param("orderId") String orderId, 
                                                       @Param("antiguedad") long antiguedad);

    @Query(value = "select r.id, u.name responsable, r.fecha_soporte, r.turno turno, r.tipo_soporte nivel, r.pais_soporte pais, r.complejidad_soporte complejidad, r.categoria_soporte categoria, s.description stream, e.error_description err, es.summary_description summary, r.codigo_tienda tienda, r.descripcion_soporte descripcion, r.analisis_soporte analisis, r.acciones_soporte acciones, r.orden_asociada orden, r.imei_asociado imei, r.sku_asociado sku, r.usuario_asociado usuario from reports r, users u, streams s, errors e, error_summary es where r.responsable_soporte = u.username and r.stream_id = s.id and r.error_id = e.id and r.error_summary_id = es.id and r.imei_asociado = :imei and r.fecha_soporte > DATE_SUB(NOW(),INTERVAL :antiguedad DAY) ORDER BY r.fecha_soporte DESC", nativeQuery = true)
    public List<ReportResult> getReportsByImeiAndDate(@Param("imei") String imei, 
                                                      @Param("antiguedad") long antiguedad);

    @Query(value = "select r.id, u.name responsable, r.fecha_soporte, r.turno turno, r.tipo_soporte nivel, r.pais_soporte pais, r.complejidad_soporte complejidad, r.categoria_soporte categoria, s.description stream, e.error_description err, es.summary_description summary, r.codigo_tienda tienda, r.descripcion_soporte descripcion, r.analisis_soporte analisis, r.acciones_soporte acciones, r.orden_asociada orden, r.imei_asociado imei, r.sku_asociado sku, r.usuario_asociado usuario from reports r, users u, streams s, errors e, error_summary es where r.responsable_soporte = u.username and r.stream_id = s.id and r.error_id = e.id and r.error_summary_id = es.id and r.sku_asociado = :sku and r.fecha_soporte > DATE_SUB(NOW(),INTERVAL :antiguedad DAY) ORDER BY r.fecha_soporte DESC", nativeQuery = true)
    public List<ReportResult> getReportsBySkuAndDate(@Param("sku") String sku, 
                                                     @Param("antiguedad") long antiguedad);

    
}

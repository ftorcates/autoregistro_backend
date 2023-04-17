package com.example.demoEncuesta.repositories;

import java.util.List;

import com.example.demoEncuesta.entities.ReportEntity;
import com.example.demoEncuesta.interfaces.StatsResult;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StatsRepository extends CrudRepository<ReportEntity, Long> {
    
    @Query(value = "SELECT s.description AS dato, count(*) AS contador FROM reports r, streams s WHERE r.stream_id = s.id AND r.fecha_soporte > DATE_SUB(NOW(),INTERVAL :antiguedad DAY) GROUP BY r.stream_id ORDER BY contador DESC", nativeQuery = true)    
    public List<StatsResult> getStatsByStreamAndDate(@Param("antiguedad") long antiguedad);

    @Query(value = "SELECT e.error_description AS dato, count(*) AS contador FROM reports r, errors e WHERE r.error_id = e.id AND r.fecha_soporte > DATE_SUB(NOW(),INTERVAL :antiguedad DAY) GROUP BY r.error_id ORDER BY contador DESC", nativeQuery = true)    
    public List<StatsResult> getStatsByErrorAndDate(@Param("antiguedad") long antiguedad);

    @Query(value = "SELECT e.summary_description AS dato, count(*) AS contador FROM reports r, error_summary e WHERE r.error_summary_id = e.id AND r.fecha_soporte > DATE_SUB(NOW(),INTERVAL :antiguedad DAY) GROUP BY r.error_summary_id ORDER BY contador DESC", nativeQuery = true)    
    public List<StatsResult> getStatsBySummaryAndDate(@Param("antiguedad") long antiguedad);

    @Query(value = "SELECT r.codigo_tienda AS dato, count(*) AS contador FROM reports r WHERE r.fecha_soporte > DATE_SUB(NOW(),INTERVAL :antiguedad DAY) GROUP BY r.codigo_tienda ORDER BY contador DESC", nativeQuery = true)    
    public List<StatsResult> getStatsByTiendaAndDate(@Param("antiguedad") long antiguedad);

    @Query(value = "SELECT r.categoria_soporte AS dato, count(*) AS contador FROM reports r WHERE r.fecha_soporte > DATE_SUB(NOW(),INTERVAL :antiguedad DAY) GROUP BY r.categoria_soporte ORDER BY contador DESC", nativeQuery = true)    
    public List<StatsResult> getStatsByCategoriaAndDate(@Param("antiguedad") long antiguedad);

    @Query(value = "SELECT u.name AS dato, count(*) AS contador FROM reports r, users u WHERE r.responsable_soporte = u.username AND r.fecha_soporte > DATE_SUB(NOW(),INTERVAL :antiguedad DAY) GROUP BY u.name ORDER BY contador DESC", nativeQuery = true)    
    public List<StatsResult> getStatsByResponsableAndDate(@Param("antiguedad") long antiguedad);

    @Query(value = "SELECT r.turno AS dato, count(*) AS contador FROM reports r WHERE r.fecha_soporte > DATE_SUB(NOW(),INTERVAL :antiguedad DAY) GROUP BY r.turno ORDER BY contador DESC", nativeQuery = true)    
    public List<StatsResult> getStatsByTurnoAndDate(@Param("antiguedad") long antiguedad);
}

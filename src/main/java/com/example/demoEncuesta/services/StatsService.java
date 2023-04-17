package com.example.demoEncuesta.services;

import java.util.List;

import com.example.demoEncuesta.interfaces.StatsResult;

public interface StatsService {

    public List<StatsResult> getStatsByStreamAndDate(long antiguedad);

    public List<StatsResult> getStatsByErrorAndDate(long antiguedad);

    public List<StatsResult> getStatsBySummaryAndDate(long antiguedad);

    public List<StatsResult> getStatsByTiendaAndDate(long antiguedad);

    public List<StatsResult> getStatsByCategoriaAndDate(long antiguedad);

    public List<StatsResult> getStatsByResponsableAndDate(long antiguedad);

    public List<StatsResult> getStatsByTurnoAndDate(long antiguedad);
    
}

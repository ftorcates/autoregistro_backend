package com.example.demoEncuesta.services;

import java.util.List;

import com.example.demoEncuesta.interfaces.StatsResult;
import com.example.demoEncuesta.repositories.StatsRepository;

import org.springframework.stereotype.Service;

@Service
public class StatsServiceImpl implements StatsService {

    StatsRepository statsRepository;

    public StatsServiceImpl(StatsRepository statsRepository){
        this.statsRepository = statsRepository;
    }

    @Override
    public List<StatsResult> getStatsByStreamAndDate(long antiguedad) {
        
        List<StatsResult> stats = this.statsRepository.getStatsByStreamAndDate(antiguedad);

        return stats;
    }

    @Override
    public List<StatsResult> getStatsByErrorAndDate(long antiguedad) {

        List<StatsResult> stats = this.statsRepository.getStatsByErrorAndDate(antiguedad);

        return stats;
    }

    @Override
    public List<StatsResult> getStatsBySummaryAndDate(long antiguedad) {
        
        List<StatsResult> stats = this.statsRepository.getStatsBySummaryAndDate(antiguedad);

        return stats;
    }

    @Override
    public List<StatsResult> getStatsByTiendaAndDate(long antiguedad) {
        
        List<StatsResult> stats = this.statsRepository.getStatsByTiendaAndDate(antiguedad);

        return stats;
    }

    @Override
    public List<StatsResult> getStatsByCategoriaAndDate(long antiguedad) {
        
        List<StatsResult> stats = this.statsRepository.getStatsByCategoriaAndDate(antiguedad);

        return stats;
    }

    @Override
    public List<StatsResult> getStatsByResponsableAndDate(long antiguedad) {
        
        List<StatsResult> stats = this.statsRepository.getStatsByResponsableAndDate(antiguedad);

        return stats;
    }

    @Override
    public List<StatsResult> getStatsByTurnoAndDate(long antiguedad) {
        
        List<StatsResult> stats = this.statsRepository.getStatsByTurnoAndDate(antiguedad);

        return stats;
    }
    
}

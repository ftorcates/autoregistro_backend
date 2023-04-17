package com.example.demoEncuesta.controllers;

import java.util.List;

import com.example.demoEncuesta.interfaces.StatsResult;
import com.example.demoEncuesta.services.StatsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class StatsController {
    
    @Autowired
    StatsService statsService;

    @GetMapping(path = "/byStreamAndDate")
    public List<StatsResult> getStatsByStreamAndDate(@RequestParam(value="antiguedad", defaultValue="1") long antiguedad, 
                                                        Authentication authentication){

        List<StatsResult> stats = statsService.getStatsByStreamAndDate(antiguedad);
      
        return stats;
    }

    @GetMapping(path = "/byErrorAndDate")
    public List<StatsResult> getStatsByErrorAndDate(@RequestParam(value="antiguedad", defaultValue="1") long antiguedad, 
                                                        Authentication authentication){

        List<StatsResult> stats = statsService.getStatsByErrorAndDate(antiguedad);
      
        return stats;
    }

    @GetMapping(path = "/bySummaryAndDate")
    public List<StatsResult> getStatsBySummaryAndDate(@RequestParam(value="antiguedad", defaultValue="1") long antiguedad, 
                                                        Authentication authentication){

        List<StatsResult> stats = statsService.getStatsBySummaryAndDate(antiguedad);
      
        return stats;
    }

    @GetMapping(path = "/byTiendaAndDate")
    public List<StatsResult> getStatsByTiendaAndDate(@RequestParam(value="antiguedad", defaultValue="1") long antiguedad, 
                                                        Authentication authentication){

        List<StatsResult> stats = statsService.getStatsByTiendaAndDate(antiguedad);
      
        return stats;
    }

    @GetMapping(path = "/byCategoriaAndDate")
    public List<StatsResult> getStatsByCategoriaAndDate(@RequestParam(value="antiguedad", defaultValue="1") long antiguedad, 
                                                        Authentication authentication){

        List<StatsResult> stats = statsService.getStatsByCategoriaAndDate(antiguedad);
      
        return stats;
    }

    @GetMapping(path = "/byResponsableAndDate")
    public List<StatsResult> getStatsByResponsableAndDate(@RequestParam(value="antiguedad", defaultValue="1") long antiguedad, 
                                                        Authentication authentication){

        List<StatsResult> stats = statsService.getStatsByResponsableAndDate(antiguedad);
      
        return stats;
    }

    @GetMapping(path = "/byTurnoAndDate")
    public List<StatsResult> getStatsByTurnoAndDate(@RequestParam(value="antiguedad", defaultValue="1") long antiguedad, 
                                                        Authentication authentication){

        List<StatsResult> stats = statsService.getStatsByTurnoAndDate(antiguedad);
      
        return stats;
    }
}

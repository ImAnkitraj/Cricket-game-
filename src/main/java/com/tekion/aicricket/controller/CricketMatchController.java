package com.tekion.aicricket.controller;


import com.tekion.aicricket.datasource.model.CricketMatch;
import com.tekion.aicricket.datasource.model.Inning;
import com.tekion.aicricket.datasource.model.Team;
import com.tekion.aicricket.datasource.repository.InningRepository;
import com.tekion.aicricket.datasource.repository.TeamRepository;
import com.tekion.aicricket.datasource.service.cricketMatch.CricketMatchDataService;
import com.tekion.aicricket.service.CricketMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cricket_matches")
public class CricketMatchController {

    @Autowired
    CricketMatchDataService service;

    @Autowired
    CricketMatchService cricketMatchService;

    @GetMapping("/")
    public List<CricketMatch> getAll() {
        return service.getAll();
    }

    @PostMapping("/create")
    public CricketMatch create(@RequestBody CricketMatch match) {
        return service.create(match);
    }

    @GetMapping("/play/{matchId}")
    public String play(@PathVariable Long matchId) {
        CricketMatch match = service.getById(matchId);
        return cricketMatchService.play(match);
    }

    @GetMapping("/result/{matchId}")
    public void result(@PathVariable Long matchId) {


//


    }
}

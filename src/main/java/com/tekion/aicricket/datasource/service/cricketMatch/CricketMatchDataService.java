package com.tekion.aicricket.datasource.service.cricketMatch;

import com.tekion.aicricket.datasource.model.CricketMatch;
import com.tekion.aicricket.datasource.model.Inning;
import com.tekion.aicricket.datasource.model.Team;
import com.tekion.aicricket.datasource.repository.CricketMatcRepository;
import com.tekion.aicricket.datasource.repository.TeamRepository;
import com.tekion.aicricket.datasource.service.inning.InningDataService;
import com.tekion.aicricket.datasource.service.team.TeamDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CricketMatchDataService {
    @Autowired
    CricketMatcRepository repository;

    @Autowired
    TeamDataService teamDataService;

    @Autowired
    InningDataService inningDataService;

    public List<CricketMatch> getAll() {
        return  repository.findAll();
    }

    public CricketMatch create(CricketMatch match) {
        return  repository.save(match);
    }
    public CricketMatch getById(long matcId) {
        return repository.findById(matcId).get();
    }

    public void result(Long matchId) {
        CricketMatch match = getById(matchId);
        Team team1 = teamDataService.getById(match.getTeam1Id());
        Team team2 = teamDataService.getById(match.getTeam2Id());
        List<Inning> innings = inningDataService.getByMatchId(matchId);


    }
}

package com.tekion.aicricket.datasource.service.team;

import com.tekion.aicricket.datasource.model.Player;
import com.tekion.aicricket.datasource.model.PlayerInningDetails;
import com.tekion.aicricket.datasource.model.Team;
import com.tekion.aicricket.datasource.repository.PlayerRepository;
import com.tekion.aicricket.datasource.repository.TeamRepository;
import com.tekion.aicricket.datasource.service.player.PlayerDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeamDataService {

    @Autowired
    TeamRepository repository;
    @Autowired
    PlayerDataService playerDataService;

    public List<Team> getAll() {
        return repository.findAll();
    }

    public Team create(Team team) {
        return repository.save(team);
    }

    public Team getById(Long teamId) {
        return repository.findById(teamId).get();
    }

    public List<Player> getPlayers(Long teamId) {
        return playerDataService.getPlayersByTeam(teamId);
    }

    @Transactional
    public void deleteById(Long teamId) {
        repository.deleteById(teamId);
    }

    @Transactional
    public Team updateById(Long teamId, Team team) throws Exception {

        Team founTeam = repository.findById(teamId).get();
        if (founTeam == null) {
            throw new Exception("Team Not Found");
        }
        team.setId(teamId);
        return repository.save(team);
    }
}

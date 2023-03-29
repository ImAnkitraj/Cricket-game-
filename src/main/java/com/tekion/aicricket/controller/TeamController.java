package com.tekion.aicricket.controller;


import com.tekion.aicricket.datasource.model.Player;
import com.tekion.aicricket.datasource.model.Team;
import com.tekion.aicricket.datasource.service.player.PlayerDataService;
import com.tekion.aicricket.datasource.service.team.TeamDataService;
import com.tekion.aicricket.dto.request.PlayerIdListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("teams")
public class TeamController {

    @Autowired
    TeamDataService service;

    @Autowired
    PlayerDataService playerDataService;

    @GetMapping
    public List<Team> getAll() {
        return service.getAll();
    }

    @PostMapping("/create")
    public Team create(@RequestBody  Team team) {
        return service.create(team);
    }

    @GetMapping("/{teamId}/players")
    public List<Player> getPlayersByTeam(@PathVariable Long teamId) {
        return service.getPlayers(teamId);
    }

    @PutMapping("/{teamId}/players/add")
    public List<Player> addPlayersToTeam(@RequestBody List<Long> playerIds, @PathVariable Long teamId) {
        return playerDataService.addPlayersToTeam(playerIds, teamId);
    }

    @DeleteMapping("/{teamId}")
    public String deleteById(@PathVariable Long teamId) {
        service.deleteById(teamId);
        return "Team " + teamId + " deleted...";
    }

    @PutMapping("/{teamId}")
    public Team updateById(@PathVariable Long teamId, @RequestBody Team team) throws Exception {
        return service.updateById(teamId, team);
    }

}

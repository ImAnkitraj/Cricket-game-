package com.tekion.aicricket.datasource.service.player;

import com.tekion.aicricket.datasource.model.Player;
import com.tekion.aicricket.datasource.model.Team;
import com.tekion.aicricket.datasource.repository.PlayerRepository;
import com.tekion.aicricket.datasource.repository.TeamRepository;
import com.tekion.aicricket.datasource.service.team.TeamDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerDataService {

    @Autowired
    private PlayerRepository repository;

    @Autowired
    private TeamRepository teamRepository;

    public List<Player> getAll() {
        return repository.findAll();
    }

    public Player create(Player player) {
        return repository.save(player);
    }

    public List<Player> getPlayersByTeam(Long teamId) {
        return repository.findByTeamId(teamId);
    }

    @Transactional
    public List<Player> addPlayersToTeam(List<Long> playerIds, Long teamId) {

        Optional<Team> teamOptional = teamRepository.findById(teamId);

        if(teamOptional.isPresent() == false) {
            throw new IllegalArgumentException("Team with id :" + teamId + " not found");
        }
        List<Player> players = new ArrayList<>();
        for(Long id: playerIds) {
            Player player = repository.findById(id).get();
            player.setTeamId(teamId);
            players.add(player);
        }
        return players;
    }

    @Transactional
    public void deleteById(Long playerId) {
        repository.deleteById(playerId);
    }

    @Transactional
    public Player updateById(Long playerId, Player player) throws Exception {

        Player foundPlayer = repository.findById(playerId).get();
        if(foundPlayer == null) {
            throw new Exception("Player not found");
        }
        player.setId(playerId);
        return repository.save(player);
    }

    public List<Player> createMany(List<Player> players) {
        return repository.saveAll(players);
    }
}

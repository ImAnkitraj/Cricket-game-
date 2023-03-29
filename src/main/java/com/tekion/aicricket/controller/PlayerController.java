package com.tekion.aicricket.controller;

import com.tekion.aicricket.datasource.model.Player;
import com.tekion.aicricket.datasource.service.player.PlayerDataService;
import com.tekion.aicricket.dto.request.PlayerListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("players")
public class PlayerController {

    @Autowired
    private PlayerDataService service;

    @GetMapping
    public List<Player> getAll() {
        return service.getAll();
    }

    @PostMapping("/create")
    public Player create(@RequestBody Player player) {
        return service.create(player);
    }

    @PostMapping("/create-many")
    public List<Player> create(@RequestBody PlayerListDto players) {
        return service.createMany(players.getPlayers());
    }
    @DeleteMapping("/{playerId}")
    public String deleteById(@PathVariable Long playerId) {
        service.deleteById(playerId);
        return "Player " + playerId + " deleted...";
    }

    @PutMapping("/{playerId}")
    public Player updateById(@PathVariable Long playerId, @RequestBody Player player) throws Exception {
        return service.updateById(playerId, player);
    }
}

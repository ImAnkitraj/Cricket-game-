package com.tekion.aicricket.controller;

import com.tekion.aicricket.datasource.model.PlayerInningDetails;
import com.tekion.aicricket.datasource.service.playerInningDetailsDataService.PlayerInningDetailsDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("player-inning-details")
@Slf4j
public class PlayerInningDetailsController {

    @Autowired
    PlayerInningDetailsDataService service;
    @GetMapping
    public List<PlayerInningDetails> getAll(){
        return service.getAll();
    }

//    @GetMapping("/{id}")
//    public PlayerInningDetails getById(@PathVariable Long id){
//        return service.getById(id);
//    }

    @GetMapping("/innings/{inningId}/players/{playerId}")
    public PlayerInningDetails getById(@PathVariable Long inningId, @PathVariable Long playerId){
        PlayerInningDetails byInningIdAndPlayerId = service.getByInningIdAndPlayerId(inningId, playerId);
        log.info("{}",byInningIdAndPlayerId);
        return byInningIdAndPlayerId;
    }
}

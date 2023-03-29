package com.tekion.aicricket.datasource.service.playerInningDetailsDataService;

import com.tekion.aicricket.datasource.model.PlayerInningDetails;
import com.tekion.aicricket.datasource.repository.PlayerInningDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerInningDetailsDataService {
    @Autowired
    PlayerInningDetailsRepository repository;

    public List<PlayerInningDetails> getAll() {
        return repository.findAll();
    }

    public PlayerInningDetails GETBYID(Long playerInningDetailsId) {
        return repository.findById(playerInningDetailsId).get();
    }

    public PlayerInningDetails getByInningIdAndPlayerId(Long inningId, Long playerId) {
        return repository.findByInningIdAndPlayerId(inningId, playerId);
    }

    public PlayerInningDetails create(PlayerInningDetails playerInningDetails) {
        return repository.save(playerInningDetails);
    }
}

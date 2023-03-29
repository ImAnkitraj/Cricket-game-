package com.tekion.aicricket.datasource.repository;

import com.tekion.aicricket.datasource.model.PlayerInningDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerInningDetailsRepository extends JpaRepository<PlayerInningDetails, Long> {
    PlayerInningDetails findByInningIdAndPlayerId(Long inningId, Long playerId);
}

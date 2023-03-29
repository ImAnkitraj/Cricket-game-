package com.tekion.aicricket.datasource.repository;

import com.tekion.aicricket.datasource.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    List<Player> findByTeamId(Long id);
}

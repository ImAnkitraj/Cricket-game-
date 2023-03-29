package com.tekion.aicricket.datasource.repository;

import com.tekion.aicricket.datasource.model.Inning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface InningRepository extends JpaRepository<Inning, Long> {

    List<Inning> findByMatchId(Long matchId);
}

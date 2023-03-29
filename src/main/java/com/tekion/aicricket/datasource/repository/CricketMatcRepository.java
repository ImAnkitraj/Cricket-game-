package com.tekion.aicricket.datasource.repository;

import com.tekion.aicricket.datasource.model.CricketMatch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CricketMatcRepository extends JpaRepository<CricketMatch, Long> {

}

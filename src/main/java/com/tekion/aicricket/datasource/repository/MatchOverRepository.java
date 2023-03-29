package com.tekion.aicricket.datasource.repository;

import com.tekion.aicricket.datasource.model.MatchOver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchOverRepository extends JpaRepository<MatchOver, Long> {

}

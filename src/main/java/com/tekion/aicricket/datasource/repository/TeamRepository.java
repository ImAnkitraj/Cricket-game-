package com.tekion.aicricket.datasource.repository;

import com.tekion.aicricket.datasource.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

}

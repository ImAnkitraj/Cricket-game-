package com.tekion.aicricket.datasource.repository;

import com.tekion.aicricket.datasource.model.Ball;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BallRepository extends JpaRepository<Ball, Long> {
    List<Ball> findByOverId(Long overId);
}

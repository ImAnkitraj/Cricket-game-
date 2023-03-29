package com.tekion.aicricket.datasource.service.ball;


import com.tekion.aicricket.datasource.model.Ball;
import com.tekion.aicricket.datasource.repository.BallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BallDataService {
    @Autowired
    BallRepository repository;

    public List<Ball> getAll() {
        return  repository.findAll();
    }

    public Ball create(Ball ball) {
        return  repository.save(ball);
    }

    public List<Ball> getByOverId(Long overId) {
        return repository.findByOverId(overId);
    }

    public Ball getById(Long id) {
        return repository.findById(id).get();
    }

}

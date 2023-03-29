package com.tekion.aicricket.controller;

import com.tekion.aicricket.datasource.model.Ball;
import com.tekion.aicricket.datasource.service.ball.BallDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("balls")
public class BallController {

    @Autowired
    BallDataService service;

    @GetMapping("/")
    public List<Ball> getAll() {
        return service.getAll();
    }

    @PostMapping("/create")
    public Ball create(@RequestBody Ball ball) {
        return service.create(ball);
    }
}

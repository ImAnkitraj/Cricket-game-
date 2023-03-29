package com.tekion.aicricket.controller;

import com.tekion.aicricket.datasource.model.MatchOver;
import com.tekion.aicricket.datasource.service.matchOver.MatchOverDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("match_overs")
public class MatchOverController {

    @Autowired
    MatchOverDataService service;

    @GetMapping("/")
    public List<MatchOver> getAll() {
        return service.getAll();
    }

    @PostMapping("/create")
    public MatchOver create(@RequestBody MatchOver over) {
        return service.create(over);
    }
}

package com.tekion.aicricket.controller;

import com.tekion.aicricket.datasource.model.Inning;
import com.tekion.aicricket.datasource.service.inning.InningDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("innings")
public class InningController {

    @Autowired
    InningDataService service;

    @GetMapping("/")
    public List<Inning> getAll() {
        return service.getAll();
    }

    @PostMapping("/create")
    public Inning create(@RequestBody Inning inning) {
        return service.create(inning);
    }


}

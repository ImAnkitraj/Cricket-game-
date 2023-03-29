package com.tekion.aicricket.datasource.service.matchOver;

import com.tekion.aicricket.datasource.model.MatchOver;
import com.tekion.aicricket.datasource.repository.MatchOverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchOverDataService {
    @Autowired
    MatchOverRepository repository;

    public List<MatchOver> getAll() {
        return  repository.findAll();
    }

    public MatchOver create(MatchOver over) {
        return  repository.save(over);
    }
}

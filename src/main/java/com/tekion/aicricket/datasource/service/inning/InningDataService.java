package com.tekion.aicricket.datasource.service.inning;

import com.tekion.aicricket.datasource.model.Inning;
import com.tekion.aicricket.datasource.repository.InningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InningDataService {
    @Autowired
    InningRepository repository;

    public List<Inning> getAll() {
        return  repository.findAll();
    }

    public Inning create(Inning inning) {
        return  repository.save(inning);
    }

    public List<Inning> getByMatchId(Long matchId) {
        return repository.findByMatchId(matchId);
    }
}

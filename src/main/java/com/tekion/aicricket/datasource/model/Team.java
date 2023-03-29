package com.tekion.aicricket.datasource.model;

import com.tekion.aicricket.datasource.service.player.PlayerDataService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name = "team")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Team {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;


}

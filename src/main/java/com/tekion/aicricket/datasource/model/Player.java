package com.tekion.aicricket.datasource.model;

import com.tekion.aicricket.enums.PlayerType;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "player")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Player {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "team_id")
    private Long teamId;

    @Enumerated(value = EnumType.STRING)
    private PlayerType type;
}

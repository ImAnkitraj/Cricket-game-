package com.tekion.aicricket.datasource.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "inning")
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Inning {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "match_id")
    private Long matchId;

    @Column(name = "batting_team_id")
    private Long battingTeamId;

    @Column(name = "balling_team_id")
    private Long ballingTeamId;

    @Column(name = "is_completed")
    private Boolean isCompleted;

    @Column(name = "runs")
    private Long runs;

    @Column(name = "wickets")
    private Long wickets;

    @Column(name = "target")
    private Long target;

    @Column(name = "max_overs")
    private Long maxOvers;

    @Column(name = "overs_done")
    private Long oversDone;

    @Column
    private Long currentBattingIndex;


    @Transient
    private Player striker;

    @Transient
    private Player nonStriker;

    @Transient
    private Player baller;


    @Transient
    private PlayerInningDetails strikerPlayerInningDetails;

    @Transient
    private PlayerInningDetails nonStrikerPlayerInningDetails;
    @Transient
    private PlayerInningDetails ballerPlayerInningDetails;


    @Transient
    private List<Player> battingTeamPlayers;

    @Transient
    private List<Player> ballingTeamPlayers;
    public void addRuns(Long runs) {
        this.runs += runs;
    }

    public void incremenWickets() {
        this.wickets++ ;
    }
}

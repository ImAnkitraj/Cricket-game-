package com.tekion.aicricket.datasource.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "player_inning_details")
public class PlayerInningDetails {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "inning_id")
    private Long inningId;

    @Column(name = "player_id")
    private Long playerId;

    @Column(name = "runs")
    private Long runs;
    @Column(name = "balls_faced")
    private Long ballsFaced;
    @Column(name = "sixes")
    private Long sixes;

    @Column(name = "fours")
    private Long fours;

    @Column(name = "balls_done")
    private Long ballsDone;

    @Column(name = "wickets")
    private Long wickets;

    @Column(name = "runs_given")
    private Long runsGiven;

    @Column(name = "is_out")
    private Boolean isOut;

    public void incrementBallsDone() {
        this.ballsDone++;
    }

    public void addRunsGiven(Long runs) {
        this.runsGiven += runs;
    }

    public void addRuns(Long runs) {
        this.runs += runs;
    }

    public void incrementWickets() {
        this.wickets++;
    }

    public void incrementSixes() {
        this.sixes++;
    }

    public void incrementFoures() {
        this.fours++;
    }
}

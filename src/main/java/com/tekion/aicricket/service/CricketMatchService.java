package com.tekion.aicricket.service;

import com.tekion.aicricket.datasource.model.*;
import com.tekion.aicricket.datasource.service.ball.BallDataService;
import com.tekion.aicricket.datasource.service.cricketMatch.CricketMatchDataService;
import com.tekion.aicricket.datasource.service.inning.InningDataService;
import com.tekion.aicricket.datasource.service.matchOver.MatchOverDataService;
import com.tekion.aicricket.datasource.service.player.PlayerDataService;
import com.tekion.aicricket.datasource.service.playerInningDetailsDataService.PlayerInningDetailsDataService;
import com.tekion.aicricket.datasource.service.team.TeamDataService;
import com.tekion.aicricket.enums.BallOutcome;
import com.tekion.aicricket.enums.PlayerType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CricketMatchService {


    @Autowired
    TeamDataService teamDataService;

    @Autowired
    CricketMatchDataService cricketMatchDataService;
    @Autowired
    InningDataService inningDataService;

    @Autowired
    BallDataService ballDataService;

    @Autowired
    PlayerDataService playerDataService;

    @Autowired
    MatchOverDataService matchOverDataService;

    @Autowired
    PlayerInningDetailsDataService playerInningDetailsDataService;


    public Inning initInning(Long matchId, Long battingTeamId, Long ballingTeamId, Long target) {
        Inning inning = Inning.builder().runs(0l).wickets(0l).target(target).matchId(matchId)
                              .ballingTeamId(ballingTeamId).battingTeamId(battingTeamId).isCompleted(false)
                              .oversDone(0l).maxOvers(20l).build();

        inning = inningDataService.create(inning);
        return inning;
    }

    public String play(CricketMatch match) {
        Inning inning1 = initInning(match.getId(), match.getTeam1Id(), match.getTeam2Id(), Long.MAX_VALUE);
        playInning(inning1);
        Inning inning2 = initInning(match.getId(), match.getTeam2Id(), match.getTeam1Id(), inning1.getRuns());
        playInning(inning2);

        Team team1 = teamDataService.getById(match.getTeam1Id());
        Team team2 = teamDataService.getById(match.getTeam2Id());

        String result = "Score : \n" + team1.getName() + " : " + inning1.getRuns() + "/" + inning1.getWickets() + "\n" +
                        team2.getName() + " : " + inning2.getRuns() + "/" + inning2.getWickets() + "\n";
        if (inning1.getRuns() > inning2.getRuns()) {
            return result + team1.getName() + " won the match by " + (inning1.getRuns() - inning2.getRuns()) + " runs";
        } else if (inning1.getRuns() < inning2.getRuns()) {
            return result + team2.getName() + " won the match by " + (10 - inning2.getWickets()) + " wickets";
        } else {
            return result + "Match drawn .... !";
        }


    }

    public PlayerInningDetails initPlayerInningDetails(Inning inning, Player player) {
        PlayerInningDetails playerInningDetails = PlayerInningDetails.builder().inningId(inning.getId()).ballsDone(0l)
                                                                     .isOut(false).fours(0l).sixes(0l).ballsFaced(0l)
                                                                     .runsGiven(0l).runs(0l).wickets(0l)
                                                                     .playerId(player.getId()).build();
        return playerInningDetailsDataService.create(playerInningDetails);
    }

    public void playInning(Inning inning) {
        Team battingTeam = teamDataService.getById(inning.getBattingTeamId());
        Team ballingTeam = teamDataService.getById(inning.getBallingTeamId());
        List<Player> battingTeamPlayers = teamDataService.getPlayers(battingTeam.getId());
        List<Player> ballingTeamPlayers = teamDataService.getPlayers(ballingTeam.getId());

        inning.setBattingTeamPlayers(battingTeamPlayers);
        inning.setBallingTeamPlayers(ballingTeamPlayers);
        inning.setStriker(battingTeamPlayers.get(0));
        inning.setStrikerPlayerInningDetails(initPlayerInningDetails(inning, inning.getStriker()));
        inning.setCurrentBattingIndex(1l);
        inning.setNonStriker(battingTeamPlayers.get(1));
        inning.setNonStrikerPlayerInningDetails(initPlayerInningDetails(inning, inning.getNonStriker()));
        inning.setBaller(ballingTeamPlayers.get(ballingTeamPlayers.size() - 1));
        inning.setBallerPlayerInningDetails(initPlayerInningDetails(inning, inning.getBaller()));

        for (Long overNumber = 1l; (overNumber <= inning.getMaxOvers()) && (inning.getWickets() < 10) &&
                                   (inning.getRuns() <= inning.getTarget()); overNumber++) {
            MatchOver matchOver = MatchOver.builder().overNo(overNumber).inningId(inning.getId())
                                           .ballerId(inning.getBaller().getId()).build();
            matchOverDataService.create(matchOver);
            throwOver(inning, matchOver);
            matchOverDataService.create(matchOver);
            swapStriker(inning);
            changeBowler(inning);
        }
    }

    private void changeBowler(Inning inning) {
        List<Player> ballingTeamPlayers = inning.getBallingTeamPlayers();
        List<Player> ballers = ballingTeamPlayers.stream().filter(p -> p.getType() == PlayerType.BALLER ||
                                                                       p.getType() == PlayerType.ALLROUNDER)
                                                 .collect(Collectors.toList());
        Random random = new Random();
        Player newBaller = ballers.get(random.nextInt(ballers.size()));
        PlayerInningDetails newBallerPLayerInningDetails = playerInningDetailsDataService.getByInningIdAndPlayerId(
                inning.getId(), newBaller.getId());

        if (newBallerPLayerInningDetails == null) {
            newBallerPLayerInningDetails = initPlayerInningDetails(inning, newBaller);
        }

        inning.setBaller(newBaller);
        inning.setBallerPlayerInningDetails(newBallerPLayerInningDetails);
    }

    private void swapStriker(Inning inning) {
        List<Player> battingTeamPlayers = inning.getBattingTeamPlayers();
        Player temp1 = inning.getStriker();
        inning.setStriker(inning.getNonStriker());
        inning.setNonStriker(temp1);
        PlayerInningDetails temp2 = inning.getStrikerPlayerInningDetails();
        inning.setStrikerPlayerInningDetails(inning.getNonStrikerPlayerInningDetails());
        inning.setNonStrikerPlayerInningDetails(temp2);
    }


    private void throwOver(Inning inning, MatchOver matchOver) {
        for (Long ballNo = 1l;
             ballNo <= 6 && (inning.getWickets() < 10) && (inning.getRuns() <= inning.getTarget()); ballNo++) {
            Ball ball = Ball.builder().overId(matchOver.getId()).ballNo(ballNo).ballerId(matchOver.getBallerId())
                            .batsmanId(inning.getStriker().getId()).outcome(calcBallOutcome(inning.getStriker()))
                            .build();
            ballDataService.create(ball);
            inning.getBallerPlayerInningDetails().incrementBallsDone();
            inning.getBallerPlayerInningDetails().addRunsGiven(ball.getOutcome().getValue());
            inning.getStrikerPlayerInningDetails().addRuns(ball.getOutcome().getValue());
            inning.addRuns(ball.getOutcome().getValue());
            if (ball.getOutcome() == BallOutcome.WICKET) {
                inning.incremenWickets();
                inning.getBallerPlayerInningDetails().incrementWickets();
                inning.getStrikerPlayerInningDetails().setIsOut(true);
                nextBatsman(inning);
            } else if (ball.getOutcome() == BallOutcome.SIX) {
                inning.getStrikerPlayerInningDetails().incrementSixes();
            } else if (ball.getOutcome() == BallOutcome.FOUR) {
                inning.getStrikerPlayerInningDetails().incrementFoures();
            }
        }
    }

    private void nextBatsman(Inning inning) {
        List<Player> players = inning.getBattingTeamPlayers();
        Long nextBatsmanIndex = inning.getCurrentBattingIndex() + 1l;
        if (nextBatsmanIndex == 11) {
            return;
        }
        Player newBatsman = players.get(Math.toIntExact(nextBatsmanIndex));
        inning.setStriker(newBatsman);
        inning.setStrikerPlayerInningDetails(initPlayerInningDetails(inning, newBatsman));
        inning.setCurrentBattingIndex(nextBatsmanIndex);
    }


    private BallOutcome calcBallOutcome(Player batsman) {
        Random random = new Random();
        List<BallOutcome> ballOutcomes = new ArrayList<>(List.of(BallOutcome.values()));

        if (batsman.getType() == PlayerType.BALLER) {
            if (random.nextInt(10) > 3) {
                List<BallOutcome> o = new ArrayList<>(List.of(BallOutcome.ONE, BallOutcome.TWO, BallOutcome.WICKET));
                return o.get(random.nextInt(o.size()));
            } else {
                return ballOutcomes.get(random.nextInt(ballOutcomes.size() - 1));
            }
        } else {
            int probability = random.nextInt(10);
            if (probability < 3) {
                return ballOutcomes.get(random.nextInt(ballOutcomes.size()));
            } else if (probability > 5) {
                return ballOutcomes.get(random.nextInt(ballOutcomes.size() - 3));
            } else {
                return ballOutcomes.get(random.nextInt(ballOutcomes.size() - 1));
            }
        }
    }
}

package com.tekion.aicricket.datasource.model;

import com.tekion.aicricket.enums.BallOutcome;
import lombok.*;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.*;

@Entity

@Table(name = "ball")
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Ball {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private BallOutcome outcome;

    @Column(name = "ball_no")
    private Long ballNo;

    @Column(name = "over_id")
    private Long overId;
    @Column(name = "baller_id")
    private Long ballerId;
    @Column(name = "batsman_id")
    private Long batsmanId;

}

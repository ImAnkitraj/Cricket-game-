package com.tekion.aicricket.datasource.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "match_over")
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class MatchOver {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "inning_id")
    private Long inningId;

    @Column(name = "over_no")
    private Long overNo;

    @Column(name = "baller_id")
    private Long ballerId;


}

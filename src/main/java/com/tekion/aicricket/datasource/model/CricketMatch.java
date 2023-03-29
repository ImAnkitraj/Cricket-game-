package com.tekion.aicricket.datasource.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "cricket_match")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CricketMatch {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "venue")
    private String venue;

    @Column(name = "team1_id")
    private Long team1Id;

    @Column(name = "team2_id")
    private Long team2Id;


}

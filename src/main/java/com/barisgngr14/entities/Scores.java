package com.barisgngr14.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Month;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Scores {

    @Id
    @Column(name = "score_id")
    private String scoreId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @Column(name = "total_score")
    private Long totalScore;

    @Enumerated(EnumType.STRING)
    @Column(name = "month")
    private Month month;
}

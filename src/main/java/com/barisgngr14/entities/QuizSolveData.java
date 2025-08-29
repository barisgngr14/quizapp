package com.barisgngr14.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="quiz_solve_data")
public class QuizSolveData {

    @Id
    @Column(name="data_id")
    private String dataId;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="quiz_id")
    private Quiz quiz;

    @Column(name = "quiz_solve_time")
    @DateTimeFormat(iso= DateTimeFormat.ISO.TIME)
    private LocalTime quizSolveTime;

    @Column(name = "correct_count")
    private Integer correctCount;

    @Column(name = "wrong_count")
    private Integer wrongCount;

    @Column(name = "unanswered_count")
    private Integer unansweredCount;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "date" , columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime date;

    @Column(name = "quiz_score")
    private Long quizScore;
}

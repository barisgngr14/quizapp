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
@Table(name = "quizzes")
public class Quizzes {

    @Id
    @Column(name = "quiz_id")
    private String quizId;

    @Column(name = "quiz_name", length = 100, nullable = false)
    private String quizName;

    @ManyToOne
    @JoinColumn(name = "quiz_group" , columnDefinition = "VARCHAR(255) DEFAULT '0'")
    private Groups quizGroup;

    @Column(name = "question_count")
    private Integer questionCount;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @Column(name = "quiz_time")
    private LocalTime quizTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "available_time")
    private LocalDateTime availableTime;
}

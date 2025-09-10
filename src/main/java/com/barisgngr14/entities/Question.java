package com.barisgngr14.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "questions")
public class Question {

    public enum Difficulty{
        EASY,
        MEDIUM,
        HARD
    }

    public enum Type{
        MULTIPLE_CHOICE,
        TRUE_FALSE,
    }

    @Id
    @Column(name = "question_id")
    private String questionId;

    @Column(name = "question_text", columnDefinition = "TEXT", nullable = false)
    private String questionText;

    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty")
    private Difficulty difficulty;

    @Enumerated(EnumType.STRING)
    @Column(name = "question_type")
    private Type questionType;

    @Column(name = "score")
    private Integer score;

}

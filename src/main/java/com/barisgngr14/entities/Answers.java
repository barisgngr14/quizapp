package com.barisgngr14.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "answers")
public class Answers {
    @Id
    @Column(name = "answer_id")
    private String answerId;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Questions question;

    @ManyToOne
    @JoinColumn(name = "option_id")
    private Options option;

    @ManyToOne
    @JoinColumn(name = "solve_data_id")
    private QuizSolveData quizSolveData;

    @Column(name = "is_correct")
    private Boolean isCorrect;
}

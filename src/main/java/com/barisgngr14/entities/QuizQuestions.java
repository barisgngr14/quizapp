package com.barisgngr14.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "quiz_questions")
@IdClass(QuizQuestionsID.class)
public class QuizQuestions {

    @Id
    @Column(name = "quiz_id")
    private String quizId;

    @Id
    @Column(name = "question_id")
    private String questionId;
}

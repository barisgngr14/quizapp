package com.barisgngr14.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "options")
public class Option {

    @Id
    @Column(name = "option_id")
    private String optionId;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(name = "option_text", columnDefinition = "TEXT", nullable = false)
    private String optionText;

    @Column(name = "is_correct")
    private Boolean isCorrect;
}

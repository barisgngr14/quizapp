package com.barisgngr14.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name="suggestions")
public class Suggestion {

    @Id
    @Column(name = "suggestion_id")
    private String suggestionId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "suggestion_text", columnDefinition = "TEXT")
    private String suggestionText;
}

package com.barisgngr14.dto;

import com.barisgngr14.entities.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoQuestion {
    private String questionId;
    private String questionText;
    private Question.Difficulty difficulty;
    private Question.Type questionType;
    private Integer score;
}

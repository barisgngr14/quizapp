package com.barisgngr14.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoQuizSolveData {
    private Integer solveTime;
    private Integer correctCount;
    private Integer wrongCount;
    private Integer unansweredCount;
    private Long quizScore;
}

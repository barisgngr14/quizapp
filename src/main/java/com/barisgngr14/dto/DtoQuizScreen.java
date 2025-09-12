package com.barisgngr14.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoQuizScreen {
    private Integer quizTime;
    private List<DtoQuestionInQuiz> questions;
}

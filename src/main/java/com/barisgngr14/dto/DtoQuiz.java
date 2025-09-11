package com.barisgngr14.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoQuiz {
    private String quizId;
    private String quizGroup;
    private String quizName;
    private LocalDateTime availableTime;
    private LocalTime quizTime;
    private Integer questionCount;
}

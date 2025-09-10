package com.barisgngr14.dto;

import com.barisgngr14.entities.Group;
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
    private Group quizGroup;
    private String quizName;
    private LocalDateTime availableTime;
    private LocalTime quizTime;
    private Integer questionCount;
}

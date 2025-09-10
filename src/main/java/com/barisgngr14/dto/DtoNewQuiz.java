package com.barisgngr14.dto;

import com.barisgngr14.entities.Group;
import com.barisgngr14.entities.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoNewQuiz {
    private String quizName;
    private String quizGroup;
    private Integer questionCount;
    private LocalTime quizTime;
    private LocalDateTime availableTime;
    private List<Question> questions;
}

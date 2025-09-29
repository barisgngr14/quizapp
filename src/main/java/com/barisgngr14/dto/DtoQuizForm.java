package com.barisgngr14.dto;

import com.barisgngr14.entities.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoQuizForm {
    private String quizId;
    private String quizName;
    private String quizGroup;
    private LocalDateTime availableTime;
    private Integer quizTime;
    private Integer questionCount;
    private List<Question> questions;
}

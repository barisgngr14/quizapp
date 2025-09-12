package com.barisgngr14.dto;

import com.barisgngr14.entities.Option;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoQuestionInQuiz {
    private String questionId;
    private String questionText;
    private String questionType;
    private List<Option> options;
}

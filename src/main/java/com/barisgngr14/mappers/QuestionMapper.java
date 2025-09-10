package com.barisgngr14.mappers;

import com.barisgngr14.dto.DtoNewQuestion;
import com.barisgngr14.entities.Option;
import com.barisgngr14.entities.Question;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

public class QuestionMapper {

    public static Question toQuestionEntity(DtoNewQuestion question){
        Question newQuestion = new Question();
        newQuestion.setQuestionId(String.valueOf(UUID.randomUUID()));
        newQuestion.setQuestionText(question.getQuestionText());
        newQuestion.setQuestionType(question.getQuestionType());
        newQuestion.setScore(question.getScore());
        newQuestion.setDifficulty(question.getDifficulty());

        return newQuestion;
    }
}

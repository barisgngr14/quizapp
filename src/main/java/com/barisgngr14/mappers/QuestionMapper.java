package com.barisgngr14.mappers;

import com.barisgngr14.dto.DtoNewQuestion;
import com.barisgngr14.dto.DtoQuestion;
import com.barisgngr14.dto.DtoQuestionInQuiz;
import com.barisgngr14.entities.Option;
import com.barisgngr14.entities.Question;

import java.util.*;

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

    public static DtoQuestion toDtoQuestion(Question question){
        DtoQuestion dtoQuestion = new DtoQuestion();
        dtoQuestion.setQuestionId(question.getQuestionId());
        dtoQuestion.setQuestionText(question.getQuestionText());
        dtoQuestion.setQuestionType(question.getQuestionType());
        dtoQuestion.setDifficulty(question.getDifficulty());
        dtoQuestion.setScore(question.getScore());

        return dtoQuestion;
    }

    public static List<DtoQuestionInQuiz> toDtoQuestionInQuiz(List<String> questionIds,
                                                              List<String> questionTexts,
                                                              Map<String, List<Option>> optionMap,
                                                              List<Question.Type> questionTypes){

        List<DtoQuestionInQuiz> dtoQuestionInQuizzes = new ArrayList<>();

        List<String> questionTypeStrings = new ArrayList<>();
        for (Question.Type type : questionTypes) {
            questionTypeStrings.add(type.name());
        }

        for (int i = 0; i < questionIds.size(); i++) {
            DtoQuestionInQuiz dto = new DtoQuestionInQuiz();
            dto.setQuestionId(questionIds.get(i));
            dto.setQuestionText(questionTexts.get(i));
            dto.setQuestionType(questionTypeStrings.get(i));
            dtoQuestionInQuizzes.add(dto);
        }

        for (DtoQuestionInQuiz dto : dtoQuestionInQuizzes) {
            String qid = dto.getQuestionId();
            List<Option> optionsForThisQuestion = optionMap.getOrDefault(qid, List.of());
            dto.setOptions(optionsForThisQuestion);
        }

        return dtoQuestionInQuizzes;

    }

}

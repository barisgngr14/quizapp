package com.barisgngr14.mappers;

import com.barisgngr14.entities.Answer;
import com.barisgngr14.entities.Option;
import com.barisgngr14.entities.QuizSolveData;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class AnswerMapper {

    public static List<Answer> toAnswerEntity(List<Option> options, QuizSolveData solveData){
        return options.stream()
                .map(o ->{
                    Answer answer = new Answer();
                    answer.setAnswerId(String.valueOf(UUID.randomUUID()));
                    answer.setOption(o);
                    answer.setQuizSolveData(solveData);
                    if(o != null){
                        answer.setQuestion(o.getQuestion());
                        answer.setIsCorrect(o.getIsCorrect());
                    }
                    return answer;
                })
                .collect(Collectors.toList());
    }

}

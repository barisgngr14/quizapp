package com.barisgngr14.mappers;

import com.barisgngr14.dto.DtoCount;
import com.barisgngr14.dto.DtoQuizSolveData;
import com.barisgngr14.entities.Option;
import com.barisgngr14.entities.Quiz;
import com.barisgngr14.entities.QuizSolveData;
import com.barisgngr14.entities.User;

import java.util.List;
import java.util.UUID;

public class SolveDataMapper {

    public static QuizSolveData toEmptySolveDataEntity(Quiz quiz, User user){
        QuizSolveData quizSolveData = new QuizSolveData();
        quizSolveData.setDataId(String.valueOf(UUID.randomUUID()));
        quizSolveData.setQuiz(quiz);
        quizSolveData.setUser(user);
        return quizSolveData;
    }

    public static DtoCount calculateCounts(List<Option> options){
        DtoCount dtoCount = new DtoCount();
        Integer correctCount = 0;
        Integer wrongCount = 0;
        Integer unansweredCount = 0;

        for(Option option : options){
            if(option == null || option.getIsCorrect() == null){
                unansweredCount++;
            }
            else if(option.getIsCorrect()){
                correctCount++;
            }else{
                wrongCount++;
            }
        }

        dtoCount.setCorrectCount(correctCount);
        dtoCount.setWrongCount(wrongCount);
        dtoCount.setUnansweredCount(unansweredCount);

        return dtoCount;
    }

    public static DtoQuizSolveData toDtoQuizSolveData(QuizSolveData quizSolveData){
        DtoQuizSolveData dtoQuizSolveData = new DtoQuizSolveData();
        dtoQuizSolveData.setSolveTime(quizSolveData.getQuizSolveTime());
        dtoQuizSolveData.setQuizScore(quizSolveData.getQuizScore());
        dtoQuizSolveData.setCorrectCount(quizSolveData.getCorrectCount());
        dtoQuizSolveData.setWrongCount(quizSolveData.getWrongCount());
        dtoQuizSolveData.setUnansweredCount(quizSolveData.getUnansweredCount());
        return dtoQuizSolveData;
    }

}

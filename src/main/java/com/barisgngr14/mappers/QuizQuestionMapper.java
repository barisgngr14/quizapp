package com.barisgngr14.mappers;

import com.barisgngr14.entities.Question;
import com.barisgngr14.entities.QuizQuestion;

import java.util.List;
import java.util.stream.Collectors;

public class QuizQuestionMapper {

    public static List<QuizQuestion> createQuizQuestionAssociation(String quizId, List<Question> questions){
        return questions.stream()
                .map(q ->{
                    QuizQuestion qq = new QuizQuestion();
                    qq.setQuizId(quizId);
                    qq.setQuestionId(q.getQuestionId());
                    return qq;
                })
                .collect(Collectors.toList());
    }
}

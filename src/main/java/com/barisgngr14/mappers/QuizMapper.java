package com.barisgngr14.mappers;

import com.barisgngr14.dto.DtoNewQuiz;
import com.barisgngr14.dto.DtoQuiz;
import com.barisgngr14.entities.Group;
import com.barisgngr14.entities.Quiz;

import java.util.UUID;

public class QuizMapper {

    public static DtoQuiz toDtoQuiz(Quiz quiz){
        DtoQuiz quizData = new DtoQuiz();
        quizData.setQuizId(quiz.getQuizId());
        quizData.setQuizGroup(quiz.getQuizGroup().getGroupName());
        quizData.setQuizTime(quiz.getQuizTime());
        quizData.setQuizName(quiz.getQuizName());
        quizData.setAvailableTime(quiz.getAvailableTime());
        quizData.setQuestionCount(quiz.getQuestionCount());
        return quizData;
    }

    public static Quiz toQuizEntity(DtoNewQuiz newQuiz){
        Quiz dbNewQuiz = new Quiz();
        Group group = new Group();
        // DEĞİŞECEK: GROUP ID Yİ İSİMDEN TARA BUL
        group.setGroupId(newQuiz.getQuizGroup());
        dbNewQuiz.setQuizId(String.valueOf(UUID.randomUUID()));
        dbNewQuiz.setQuizName(newQuiz.getQuizName());
        dbNewQuiz.setQuizGroup(group);
        dbNewQuiz.setQuizTime(newQuiz.getQuizTime());
        dbNewQuiz.setQuestionCount(newQuiz.getQuestionCount());
        dbNewQuiz.setAvailableTime(newQuiz.getAvailableTime());
        return dbNewQuiz;
    }

}

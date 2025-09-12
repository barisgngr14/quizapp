package com.barisgngr14.services;

import com.barisgngr14.dto.*;

import java.util.List;

public interface IQuestionService {
    boolean addQuestion(DtoNewQuestion dtoNewQuestion);
    List<DtoQuestion> getAllQuestions();
    DtoQuizScreen fetchSelectedQuizQuestions(DtoQuizId quizId);
}

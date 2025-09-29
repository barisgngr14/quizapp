package com.barisgngr14.services;

import com.barisgngr14.dto.*;

import java.util.List;

public interface IQuestionService {
    boolean addQuestion(DtoNewQuestion dtoNewQuestion);
    List<DtoQuestionForm> getAllQuestions();
    DtoQuizScreen fetchSelectedQuizQuestions(DtoQuizId quizId);
}

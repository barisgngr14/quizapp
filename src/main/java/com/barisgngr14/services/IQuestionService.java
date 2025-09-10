package com.barisgngr14.services;

import com.barisgngr14.dto.DtoNewQuestion;
import com.barisgngr14.dto.DtoQuestion;

import java.util.List;

public interface IQuestionService {
    boolean addQuestion(DtoNewQuestion dtoNewQuestion);
    List<DtoQuestion> getAllQuestions();
}

package com.barisgngr14.services;

import com.barisgngr14.dto.DtoNewQuiz;
import com.barisgngr14.dto.DtoQuiz;

import java.util.List;

public interface IQuizService {
    List<DtoQuiz> fetchAllQuizzes();
    boolean addQuiz(DtoNewQuiz dtonewQuiz);
}

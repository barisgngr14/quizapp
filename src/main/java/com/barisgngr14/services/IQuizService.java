package com.barisgngr14.services;

import com.barisgngr14.dto.DtoNewQuiz;
import com.barisgngr14.dto.DtoQuiz;
import com.barisgngr14.dto.DtoQuizForm;

import java.util.List;

public interface IQuizService {
    List<DtoQuizForm> fetchAllQuizzes();
    boolean addQuiz(DtoNewQuiz dtonewQuiz);
    List<DtoQuiz> fetchAssignedQuizzes(String token);
}

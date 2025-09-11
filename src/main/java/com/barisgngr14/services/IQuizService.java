package com.barisgngr14.services;

import com.barisgngr14.dto.DtoNewQuiz;
import com.barisgngr14.dto.DtoQuiz;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IQuizService {
    List<DtoQuiz> fetchAllQuizzes();
    boolean addQuiz(DtoNewQuiz dtonewQuiz);
    List<DtoQuiz> fetchAssignedQuizzes(String token);
}

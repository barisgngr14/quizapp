package com.barisgngr14.services;

import com.barisgngr14.dto.DtoAnswer;
import com.barisgngr14.dto.DtoQuizSolveData;

public interface IAnswerService {

    DtoQuizSolveData saveAnswers(DtoAnswer quizPayload);

}

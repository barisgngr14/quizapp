package com.barisgngr14.services;

import com.barisgngr14.dto.DtoQuizId;
import com.barisgngr14.dto.DtoQuizSolveData;
import com.barisgngr14.entities.Option;
import com.barisgngr14.entities.QuizSolveData;

import java.util.List;

public interface ISolveDataService {
    String createEmptySolveData(DtoQuizId quizId, String token);
    DtoQuizSolveData updateQuizSolveData(QuizSolveData emptyQuizSolveData, Integer solveTime, List<Option> options, Integer timeLeft);
}

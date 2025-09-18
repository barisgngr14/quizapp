package com.barisgngr14.services.impl;

import com.barisgngr14.dto.DtoAnswer;
import com.barisgngr14.dto.DtoOption;
import com.barisgngr14.dto.DtoQuizSolveData;
import com.barisgngr14.entities.Answer;
import com.barisgngr14.entities.Option;
import com.barisgngr14.entities.QuizSolveData;
import com.barisgngr14.mappers.AnswerMapper;
import com.barisgngr14.repositories.AnswerRepository;
import com.barisgngr14.repositories.QuizSolveDataRepository;
import com.barisgngr14.services.IAnswerService;
import com.barisgngr14.services.ISolveDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnswerServiceImpl implements IAnswerService {

    @Autowired
    private QuizSolveDataRepository quizSolveDataRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private ISolveDataService solveDataService;

    @Override
    public DtoQuizSolveData saveAnswers(DtoAnswer quizPayload) {
        Map<String, DtoOption> answersMap = quizPayload.getAnswers();
        List<DtoOption> answers = new ArrayList<>(answersMap.values());
        List<Option> allOptions = answers.stream()
                .map(DtoOption::getSelectedOption)
                .collect(Collectors.toList());
        Optional<QuizSolveData> optional = quizSolveDataRepository.findById(quizPayload.getSolveDataId());
        if(optional.isPresent()){
            QuizSolveData quizSolveData = optional.get();
            List<Answer> answerList = AnswerMapper.toAnswerEntity(allOptions, quizSolveData);
            try{
                answerRepository.saveAll(answerList);
                return solveDataService.updateQuizSolveData(quizSolveData, quizPayload.getSolveTime(), allOptions, quizPayload.getTimeLeft());
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }

        }

        return null;
    }
}

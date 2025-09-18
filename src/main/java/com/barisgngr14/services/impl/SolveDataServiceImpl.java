package com.barisgngr14.services.impl;

import com.barisgngr14.dto.DtoCount;
import com.barisgngr14.dto.DtoQuizId;
import com.barisgngr14.dto.DtoQuizSolveData;
import com.barisgngr14.entities.Option;
import com.barisgngr14.entities.Quiz;
import com.barisgngr14.entities.QuizSolveData;
import com.barisgngr14.entities.User;
import com.barisgngr14.mappers.SolveDataMapper;
import com.barisgngr14.repositories.QuizRepository;
import com.barisgngr14.repositories.QuizSolveDataRepository;
import com.barisgngr14.repositories.UserRepository;
import com.barisgngr14.services.IAuthService;
import com.barisgngr14.services.IScoreService;
import com.barisgngr14.services.ISolveDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class SolveDataServiceImpl implements ISolveDataService {

    @Autowired
    private IAuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizSolveDataRepository quizSolveDataRepository;

    @Autowired
    private IScoreService scoreService;

    @Override
    public String createEmptySolveData(DtoQuizId quizId, String token) {
        String username = authService.extractUsername(token);
        Optional<User> optionalUser = userRepository.findByUsername(username);
        Optional<Quiz> optionalQuiz = quizRepository.findById(quizId.getQuizId());
        if(optionalUser.isPresent() && optionalQuiz.isPresent()){
            User dbUser = optionalUser.get();
            Quiz dbQuiz = optionalQuiz.get();
            QuizSolveData dbQuizSolveData = SolveDataMapper.toEmptySolveDataEntity(dbQuiz, dbUser);
            try{
                quizSolveDataRepository.save(dbQuizSolveData);
                return dbQuizSolveData.getDataId();
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        }

        return null;
    }

    @Override
    public DtoQuizSolveData updateQuizSolveData(QuizSolveData emptyQuizSolveData, Integer solveTime, List<Option> options, Integer timeLeft) {
        emptyQuizSolveData.setQuizSolveTime(solveTime);
        ZoneId turkeyZone = ZoneId.of("Europe/Istanbul");
        LocalDateTime turkeyTime = LocalDateTime.now(turkeyZone);
        emptyQuizSolveData.setDate(turkeyTime);

        Long quizScore = scoreService.calculateQuizScore(timeLeft, options);
        DtoCount counts = SolveDataMapper.calculateCounts(options);

        emptyQuizSolveData.setQuizScore(quizScore);
        emptyQuizSolveData.setCorrectCount(counts.getCorrectCount());
        emptyQuizSolveData.setWrongCount(counts.getWrongCount());
        emptyQuizSolveData.setUnansweredCount(counts.getUnansweredCount());

        try{
            quizSolveDataRepository.save(emptyQuizSolveData);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

        return SolveDataMapper.toDtoQuizSolveData(emptyQuizSolveData);
    }
}

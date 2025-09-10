package com.barisgngr14.services.impl;

import com.barisgngr14.dto.DtoNewQuiz;
import com.barisgngr14.dto.DtoQuiz;
import com.barisgngr14.entities.Quiz;
import com.barisgngr14.entities.QuizQuestion;
import com.barisgngr14.mappers.QuizQuestionMapper;
import com.barisgngr14.repositories.QuizQuestionRepository;
import com.barisgngr14.repositories.QuizRepository;
import com.barisgngr14.services.IQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.barisgngr14.mappers.QuizMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements IQuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizQuestionRepository quizQuestionRepository;

    @Override
    public List<DtoQuiz> fetchAllQuizzes(){
        List<Quiz> dbQuizzes = quizRepository.findAll();

        return dbQuizzes.stream()
                .map(QuizMapper::toDtoQuiz)
                .collect(Collectors.toList());
    }

    @Override
    public boolean addQuiz(DtoNewQuiz dtonewQuiz) {
        Quiz quiz = QuizMapper.toQuizEntity(dtonewQuiz);
        try {
            quizRepository.save(quiz);
            List<QuizQuestion> associations = QuizQuestionMapper
                    .createQuizQuestionAssociation(quiz.getQuizId(), dtonewQuiz.getQuestions());

            quizQuestionRepository.saveAll(associations);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

package com.barisgngr14.services.impl;

import com.barisgngr14.dto.DtoNewQuiz;
import com.barisgngr14.dto.DtoQuiz;
import com.barisgngr14.entities.Quiz;
import com.barisgngr14.entities.QuizQuestion;
import com.barisgngr14.entities.User;
import com.barisgngr14.mappers.QuizQuestionMapper;
import com.barisgngr14.repositories.GroupRepository;
import com.barisgngr14.repositories.QuizQuestionRepository;
import com.barisgngr14.repositories.QuizRepository;
import com.barisgngr14.repositories.UserRepository;
import com.barisgngr14.services.IAuthService;
import com.barisgngr14.services.IQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.barisgngr14.mappers.QuizMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements IQuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizQuestionRepository quizQuestionRepository;

    @Autowired
    private IAuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

   // @Transactional
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

    @Override
    public List<DtoQuiz> fetchAssignedQuizzes(String token) {
        String username = authService.extractUsername(token);
        String userId = userRepository.findByUsername(username)
                .map(User::getUserId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        List<String> groupIds = groupRepository.findGroupIdsByUserId(userId);
        List<Quiz> user_quizzes = quizRepository.findAllByGroupIds(groupIds);
        return user_quizzes.stream()
                .map(QuizMapper::toDtoQuiz)
                .collect(Collectors.toList());

    }
}

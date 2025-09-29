package com.barisgngr14.services.impl;

import com.barisgngr14.dto.*;
import com.barisgngr14.entities.Option;
import com.barisgngr14.entities.Question;
import com.barisgngr14.mappers.OptionMapper;
import com.barisgngr14.mappers.QuestionMapper;
import com.barisgngr14.repositories.OptionRepository;
import com.barisgngr14.repositories.QuestionRepository;
import com.barisgngr14.repositories.QuizQuestionRepository;
import com.barisgngr14.repositories.QuizRepository;
import com.barisgngr14.services.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements IQuestionService {

    @Autowired
    private final QuestionRepository questionRepository;

    @Autowired
    private final OptionRepository optionRepository;

    @Autowired
    private QuizQuestionRepository quizQuestionRepository;

    @Autowired
    private QuizRepository quizRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository, OptionRepository optionRepository) {
        this.questionRepository = questionRepository;
        this.optionRepository = optionRepository;
    }

    @Override
    public boolean addQuestion(DtoNewQuestion dtoNewQuestion) {
        Question question = QuestionMapper.toQuestionEntity(dtoNewQuestion);
        List<Option> options = OptionMapper.toOptionEntity(dtoNewQuestion.getOptions(), question);
        try {
            questionRepository.save(question);
            optionRepository.saveAll(options);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<DtoQuestionForm> getAllQuestions() {
        List<Question> dbQuestions = questionRepository.findAll();

        List<String> questionIds = dbQuestions.stream()
                .map(Question::getQuestionId)
                .toList();

        Map<String, List<Option>> questionIdToOptions = questionIds.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        optionRepository::findOptionsByQuestion_QuestionId
                ));

        return dbQuestions.stream()
                .map(question -> {
                    List<Option> options = questionIdToOptions.getOrDefault(question.getQuestionId(), Collections.emptyList());
                    return new DtoQuestionForm(
                            question.getQuestionId(),
                            question.getQuestionText(),
                            question.getDifficulty(),
                            question.getQuestionType(),
                            question.getScore(),
                            options
                    );
                })
                .toList();

    }

    @Override
    public DtoQuizScreen fetchSelectedQuizQuestions(DtoQuizId quizId) {

        List<String> questionIds = quizQuestionRepository.findQuestionIdsByQuizId(quizId.getQuizId());
        List<String> questionTexts = questionRepository.findQuestionTexts(questionIds);
        List<Option> options = optionRepository.findByQuestionIdIn(questionIds);
        Map<String, List<Option>> optionMap = options.stream()
                .collect(Collectors.groupingBy(o -> o.getQuestion().getQuestionId()));
        List<Question.Type> questionTypes = questionRepository.findQuestionTypes(questionIds);

        List<DtoQuestionInQuiz> questions = QuestionMapper.toDtoQuestionInQuiz(questionIds, questionTexts, optionMap, questionTypes);
        Integer quizTime = quizRepository.findQuizTime(quizId.getQuizId());

        DtoQuizScreen dto = new DtoQuizScreen();
        dto.setQuestions(questions);
        dto.setQuizTime(quizTime);
        return dto;
    }
}

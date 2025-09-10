package com.barisgngr14.services.impl;

import com.barisgngr14.dto.DtoNewQuestion;
import com.barisgngr14.dto.DtoQuestion;
import com.barisgngr14.entities.Option;
import com.barisgngr14.entities.Question;
import com.barisgngr14.mappers.OptionMapper;
import com.barisgngr14.mappers.QuestionMapper;
import com.barisgngr14.repositories.OptionRepository;
import com.barisgngr14.repositories.QuestionRepository;
import com.barisgngr14.services.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements IQuestionService {

    @Autowired
    private final QuestionRepository questionRepository;

    @Autowired
    private final OptionRepository optionRepository;

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
    public List<DtoQuestion> getAllQuestions() {
        List<Question> dbQuestions = questionRepository.findAll();

        return dbQuestions.stream()
                .map(QuestionMapper::toDtoQuestion)
                .collect(Collectors.toList());
    }
}

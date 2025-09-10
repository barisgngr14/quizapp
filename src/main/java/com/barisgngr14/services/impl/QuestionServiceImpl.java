package com.barisgngr14.services.impl;

import com.barisgngr14.dto.DtoNewQuestion;
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
}

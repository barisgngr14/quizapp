package com.barisgngr14.services.impl;

import com.barisgngr14.entities.Option;
import com.barisgngr14.services.IScoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceImpl implements IScoreService {

    @Override
    public Long calculateQuizScore(long initialScore, List<Option> options) {

        for(Option option : options){
            if (option == null || option.getIsCorrect() == null || option.getQuestion() == null) {
                continue;
            }

            if(option.getIsCorrect()){
                initialScore += option.getQuestion().getScore();
            } else {
                initialScore -= option.getQuestion().getScore();
            }
        }

        return initialScore;
    }

}

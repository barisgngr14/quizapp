package com.barisgngr14.services;

import com.barisgngr14.entities.Option;

import java.util.List;

public interface IScoreService {
    Long calculateQuizScore(long initialScore, List<Option> options);
}

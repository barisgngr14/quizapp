package com.barisgngr14.controller;

import com.barisgngr14.dto.DtoNewQuiz;
import com.barisgngr14.dto.DtoQuiz;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IQuizController {
    ResponseEntity<List<DtoQuiz>> fetchAllQuizzes();
    ResponseEntity<?> addQuiz(DtoNewQuiz dtoNewQuiz);
}

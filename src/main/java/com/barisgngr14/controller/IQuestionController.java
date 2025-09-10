package com.barisgngr14.controller;

import com.barisgngr14.dto.DtoNewQuestion;
import com.barisgngr14.dto.DtoQuestion;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IQuestionController {
    ResponseEntity<?> addQuestion(DtoNewQuestion dtoNewQuestion);
    ResponseEntity<List<DtoQuestion>> getAllQuestions();
}

package com.barisgngr14.controller;

import com.barisgngr14.dto.DtoNewQuestion;
import org.springframework.http.ResponseEntity;

public interface IQuestionController {
    ResponseEntity<?> addQuestion(DtoNewQuestion dtoNewQuestion);
}

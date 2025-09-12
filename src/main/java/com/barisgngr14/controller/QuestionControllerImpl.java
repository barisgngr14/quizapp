package com.barisgngr14.controller;

import com.barisgngr14.dto.*;
import com.barisgngr14.services.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(path = "/api/questions")
public class QuestionControllerImpl {

    @Autowired
    private final IQuestionService questionService;

    public QuestionControllerImpl(IQuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping(path="/add")
    public ResponseEntity<?> addQuestion(@RequestBody DtoNewQuestion dtoNewQuestion) {
        try{
            questionService.addQuestion(dtoNewQuestion);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }

    }

    @GetMapping(path="/fetch")
    public ResponseEntity<List<DtoQuestion>> getAllQuestions() {
        List<DtoQuestion> dtoQuestionList = questionService.getAllQuestions();
        return ResponseEntity.ok(dtoQuestionList);
    }

    @PostMapping(path = "/by-quiz")
    public ResponseEntity<DtoQuizScreen> fetchSelectedQuizQuestions(@RequestBody DtoQuizId quizId) {
        return ResponseEntity.ok(questionService.fetchSelectedQuizQuestions(quizId));
    }
}

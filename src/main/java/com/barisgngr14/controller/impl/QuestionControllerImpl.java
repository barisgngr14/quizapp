package com.barisgngr14.controller.impl;

import com.barisgngr14.controller.IQuestionController;
import com.barisgngr14.dto.DtoNewQuestion;
import com.barisgngr14.dto.DtoQuestion;
import com.barisgngr14.services.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(path = "/api/questions")
public class QuestionControllerImpl implements IQuestionController {

    @Autowired
    private final IQuestionService questionService;

    public QuestionControllerImpl(IQuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping(path="/add")
    @Override
    public ResponseEntity<?> addQuestion(@RequestBody DtoNewQuestion dtoNewQuestion) {
        try{
            questionService.addQuestion(dtoNewQuestion);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }

    }

    @GetMapping(path="/fetch")
    @Override
    public ResponseEntity<List<DtoQuestion>> getAllQuestions() {
        List<DtoQuestion> dtoQuestionList = questionService.getAllQuestions();
        return ResponseEntity.ok(dtoQuestionList);
    }

}

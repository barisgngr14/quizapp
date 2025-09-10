package com.barisgngr14.controller.impl;

import com.barisgngr14.controller.IQuizController;
import com.barisgngr14.dto.DtoNewQuiz;
import com.barisgngr14.dto.DtoQuiz;
import com.barisgngr14.services.IQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(path = "/api/quizzes")
public class QuizControllerImpl implements IQuizController {

    @Autowired
    private final IQuizService quizService;

    public QuizControllerImpl(IQuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping(path="/fetch")
    @Override
    public ResponseEntity<List<DtoQuiz>> fetchAllQuizzes(){
        List<DtoQuiz> dtoQuizList = quizService.fetchAllQuizzes();
        return ResponseEntity.ok(dtoQuizList);
    }

    @PostMapping(path="/add")
    @Override
    public ResponseEntity<?> addQuiz(@RequestBody DtoNewQuiz dtoNewQuiz) {

        try {
            quizService.addQuiz(dtoNewQuiz);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }
}

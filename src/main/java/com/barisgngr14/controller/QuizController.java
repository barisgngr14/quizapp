package com.barisgngr14.controller;

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
public class QuizController {

    @Autowired
    private IQuizService quizService;

    @GetMapping(path = "/fetch")
    public ResponseEntity<List<DtoQuiz>> fetchAllQuizzes() {
        return ResponseEntity.ok(quizService.fetchAllQuizzes());
    }

    @PostMapping(path = "/add")
    public ResponseEntity<?> addQuiz(@RequestBody DtoNewQuiz dtoNewQuiz) {

        try {
            quizService.addQuiz(dtoNewQuiz);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @GetMapping(path = "/assigned")
    public ResponseEntity<List<DtoQuiz>> fetchAsssignedQuizzes(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(quizService.fetchAssignedQuizzes(token));
    }
}

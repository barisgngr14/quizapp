package com.barisgngr14.controller;

import com.barisgngr14.dto.DtoAnswer;
import com.barisgngr14.dto.DtoQuizSolveData;
import com.barisgngr14.services.IAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(path = "/api/answer")
public class AnswerController {

    @Autowired
    private IAnswerService answerService;

    @PostMapping(path = "/save")
    public ResponseEntity<DtoQuizSolveData> saveAnswers(@RequestBody DtoAnswer quizPayload){
        return ResponseEntity.ok(answerService.saveAnswers(quizPayload));
    }

}

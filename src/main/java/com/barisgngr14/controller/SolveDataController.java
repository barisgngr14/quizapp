package com.barisgngr14.controller;

import com.barisgngr14.dto.DtoQuizId;
import com.barisgngr14.services.ISolveDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(path = "/api/solvedata")
public class SolveDataController {

    @Autowired
    private ISolveDataService solveDataService;

    @PostMapping(path = "/create")
    public ResponseEntity<?> createEmptySolveData(@RequestBody DtoQuizId quizId, @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(solveDataService.createEmptySolveData(quizId, token));
    }

}

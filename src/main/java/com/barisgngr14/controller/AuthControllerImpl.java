package com.barisgngr14.controller;

import com.barisgngr14.dto.DtoLoginResponse;
import com.barisgngr14.dto.DtoUserLogin;
import com.barisgngr14.dto.DtoUserSignUp;
import com.barisgngr14.services.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(path = "/api")
public class AuthControllerImpl {

    @Autowired
    private final IAuthService authService;

    public AuthControllerImpl(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<Map<String, String>> getUserToken(@RequestBody DtoUserLogin userLoginData){
        String token = authService.getUserToken(userLoginData);
        return ResponseEntity.ok(Map.of("token", token));
    }

    @GetMapping(path = "/verify")
    public DtoLoginResponse getUserDetail(@RequestHeader("Authorization") String token){
        return authService.getUserDetail();
    }

    @PostMapping("/signup")
    public boolean handleSignUp(@RequestBody DtoUserSignUp signupData) {
        return authService.handleSignUp(signupData);
    }
}

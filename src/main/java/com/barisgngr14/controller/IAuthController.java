package com.barisgngr14.controller;

import com.barisgngr14.dto.DtoLoginResponse;
import com.barisgngr14.dto.DtoUserLogin;
import com.barisgngr14.dto.DtoUserSignUp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface IAuthController {
    public ResponseEntity<Map<String, String>> getUserToken(@RequestBody DtoUserLogin userLoginData);
    public DtoLoginResponse getUserDetail(String token);
    public boolean handleSignUp (DtoUserSignUp userSignUp);
}

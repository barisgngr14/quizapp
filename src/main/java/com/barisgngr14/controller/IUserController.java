package com.barisgngr14.controller;

import com.barisgngr14.dto.DtoLoginResponse;
import org.springframework.http.ResponseEntity;

public interface IUserController {
    public ResponseEntity<DtoLoginResponse> getUserRole(String userId);
}

package com.barisgngr14.services;

import com.barisgngr14.dto.DtoLoginResponse;
import com.barisgngr14.dto.DtoUserLogin;
import com.barisgngr14.dto.DtoUserSignUp;

public interface IAuthService {
    String getUserToken(DtoUserLogin userLoginData);
    boolean handleSignUp(DtoUserSignUp signupData);
    DtoLoginResponse getUserDetail();
    String extractUsername(String token);
}

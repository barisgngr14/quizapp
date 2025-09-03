package com.barisgngr14.services;

import com.barisgngr14.dto.DtoLoginResponse;
import com.barisgngr14.dto.DtoUserLogin;
import com.barisgngr14.dto.DtoUserSignUp;
import com.barisgngr14.entities.User;

public interface IAuthService {
    public String getUserToken(DtoUserLogin userLoginData);
    public boolean handleSignUp(DtoUserSignUp signupData);
    public DtoLoginResponse getUserDetail();
}

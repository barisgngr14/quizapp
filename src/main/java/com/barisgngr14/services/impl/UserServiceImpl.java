package com.barisgngr14.services.impl;

import com.barisgngr14.dto.DtoUserResponse;
import com.barisgngr14.entities.User;
import com.barisgngr14.repositories.UserRepository;
import com.barisgngr14.services.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public DtoUserResponse getUserRole(String userId) {
        DtoUserResponse response = new DtoUserResponse();
        Optional<User> optional = userRepository.findById(userId);
        if(optional.isPresent()){
            User user = optional.get();
            BeanUtils.copyProperties(user, response);
            return response;
        }
        return null;
    }
}

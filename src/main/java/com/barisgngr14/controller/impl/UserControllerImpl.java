package com.barisgngr14.controller.impl;

import com.barisgngr14.controller.IUserController;
import com.barisgngr14.dto.DtoLoginResponse;
import com.barisgngr14.dto.DtoUserResponse;
import com.barisgngr14.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(path = "/api")
public class UserControllerImpl implements IUserController {

    @Autowired
    private IUserService userService;

    @GetMapping(path = "/login/{id}")
    @Override
    public ResponseEntity<DtoLoginResponse> getUserRole(@PathVariable(name = "id") String userId){
        DtoUserResponse dbUser = userService.getUserRole(userId);
        DtoLoginResponse response = new DtoLoginResponse();
        response.setUser(dbUser);
        if (dbUser == null) {
            return ResponseEntity.notFound().build();
        }
        else if("ADMIN".equals(dbUser.getRole().name())){
            response.setRedirectPath("/admin/dashboard");
        }
        else{
            response.setRedirectPath("/app");
        }
        return ResponseEntity.ok(response);
    }
}

package com.barisgngr14.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoUserSignUp {
    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;
}

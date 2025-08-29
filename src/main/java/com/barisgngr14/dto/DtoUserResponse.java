package com.barisgngr14.dto;

import com.barisgngr14.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoUserResponse {
    private String name;
    private String surname;
    private String username;

    @JsonIgnore
    private User.Role role;
}

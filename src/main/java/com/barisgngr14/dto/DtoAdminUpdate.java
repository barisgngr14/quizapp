package com.barisgngr14.dto;

import com.barisgngr14.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoAdminUpdate {
    private String id;
    private String username;
    private String email;
    private User.Role role;
}

package com.test.dto.incoming;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CustomUserCommand {

    private String email;
    private String password;
    private String name;
    private List<String> userRoles;

//    public CustomUserCommand(String email, String password, String name, List<String> userRoles) {
//        this.email = email;
//        this.password = password;
//        this.name = name;
//        this.userRoles = userRoles;
//    }
}

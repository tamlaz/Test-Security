package com.test.controller;

import com.test.dto.incoming.CustomUserCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.test.service.JpaUserDetailsService;

@RestController
@RequestMapping("api/users")
public class CustomUserController {

    private final JpaUserDetailsService jpaUserDetailsService;


    @Autowired
    public CustomUserController(JpaUserDetailsService jpaUserDetailsService) {
        this.jpaUserDetailsService = jpaUserDetailsService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody CustomUserCommand customUserCommand) {
        jpaUserDetailsService.register(customUserCommand);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<String> getTest() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}

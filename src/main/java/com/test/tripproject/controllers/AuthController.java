package com.test.tripproject.controllers;

import com.test.tripproject.model.dtos.UserDetailsDTO;
import com.test.tripproject.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth/users/")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody UserDetailsDTO user){

        try{
            int userId = authService.registerUser(user);

            return ResponseEntity
                    .ok()
                    .body(
                            String.format("CREATED USER WITH ID = %d", userId)
                    );
        }catch(Exception e){
            return ResponseEntity
                    .ok()
                    .body(
                            e.getMessage()
                    );
        }
    }
}
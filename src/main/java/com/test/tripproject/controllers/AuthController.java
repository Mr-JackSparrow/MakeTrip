package com.test.tripproject.controllers;

import com.test.tripproject.model.dtos.UserDTO;
import com.test.tripproject.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService userService;

    public AuthController(AuthService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody UserDTO user){
        return ResponseEntity
                .ok()
                .body(
                        userService.registerUser(user)
                );
    }

    @RequestMapping(value = "/getCsrfToken")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> getToken(HttpServletRequest request){

        return ResponseEntity
                .ok()
                .body(((CsrfToken) request.getAttribute("_csrf")).getToken());

    }
}
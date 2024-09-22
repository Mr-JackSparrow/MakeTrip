package com.test.tripproject.controllers;

import com.test.tripproject.model.entities.UserRegistrationEntity;
import com.test.tripproject.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String createUser(@RequestBody UserRegistrationEntity user){
        return userService.insertUser(user);
    }

    @RequestMapping(value = "/getCsrfToken")
    @PreAuthorize("hasRole('ADMIN')")
    public String getToken(HttpServletRequest request){
        return ((CsrfToken) request.getAttribute("_csrf")).getToken();
    }
}
package com.test.tripproject.security.services;

import com.test.tripproject.exceptions.UserNotFoundException;
import com.test.tripproject.model.dtos.LoginDTO;
import com.test.tripproject.repositories.UserDao;
import com.test.tripproject.repositories.impls.UserDaoImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    private final UserDao userDao;

    public CustomUserDetailsService(UserDaoImpl userDao){
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try{

            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + username);

            LoginDTO loginDto = userDao.findCredentialsByEmailId(username);



            return User
                    .withUsername(username)
                    .password("{noop}" + loginDto.getPassword())
                    .roles("ADMIN")
                    .build();

        }catch(UserNotFoundException e){
            System.out.println("exception");
            throw new UsernameNotFoundException(String.format("User with username : %s not found", username));
        }

    }

}

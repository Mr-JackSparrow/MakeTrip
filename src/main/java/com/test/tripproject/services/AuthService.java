package com.test.tripproject.services;

import com.test.tripproject.exceptions.CustomUserException;
import com.test.tripproject.model.dtos.UserDetailsDTO;
import com.test.tripproject.repositories.UserDao;
import com.test.tripproject.repositories.impls.UserDaoImpl;
import org.springframework.stereotype.Service;

import static com.test.tripproject.services.utils.Converter.convertUserDetailsDtoToEntity;
import static com.test.tripproject.services.utils.Converter.convertUserDtoToEntity;

@Service
public class AuthService {


    private final UserDao userDao;

    public AuthService(UserDaoImpl userDao){
        this.userDao = userDao;
    }

    public int registerUser(UserDetailsDTO user){

        if(userDao.findUserByEmailId(user.getEmailId()) != null){
            throw new CustomUserException(String.format("USER WITH : (%s) ALREADY EXISTS IN DATABASE", user.getEmailId()));
        }else{
            int userId = userDao.insert(convertUserDetailsDtoToEntity(user));

            if(userId <= 0) {
                throw new CustomUserException(String.format("USER WITH : (%s) WAS NOT UPDATED", user.getEmailId()));
            }else{
                return userId;
            }
        }
    }
}
package com.test.tripproject.services;

import com.test.tripproject.exceptions.CustomException;
import com.test.tripproject.model.dtos.requestDTOs.RequestCreateUserDTO;
import com.test.tripproject.repositories.UserDAO;
import org.springframework.stereotype.Service;

import static com.test.tripproject.services.utils.Converter.convertCreateUserDtoToUserEntity;

@Service
public class AuthService {


    private final UserDAO userDao;

    public AuthService(UserDAO userDao){
        this.userDao = userDao;
    }

    public int registerUser(RequestCreateUserDTO user){

        if(userDao.findUserByEmailId(user.getEmailId()) != null){
            throw new CustomException(String.format("USER WITH : (%s) ALREADY EXISTS IN DATABASE", user.getEmailId()));
        }else{
            int userId = userDao.insert(convertCreateUserDtoToUserEntity(user));

            if(userId <= 0) {
                throw new CustomException("USER WAS NOT CREATED");
            }else{
                return userId;
            }
        }
    }
}
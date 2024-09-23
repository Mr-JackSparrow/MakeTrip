package com.test.tripproject.services;

import com.test.tripproject.model.dtos.UserDTO;
import com.test.tripproject.model.entities.UserEntity;
import com.test.tripproject.repositories.UserDao;
import org.springframework.stereotype.Service;

@Service
public class AuthService {


    private final UserDao userDao;

    public AuthService(UserDao userDao){
        this.userDao = userDao;
    }

    public String registerUser(UserDTO user){
        int id = userDao.insert(convertDtoToEntity(user));
        return (id != 0) ? "User created" : "User could not be created";
    }

    private UserEntity convertDtoToEntity(UserDTO user){
        return new UserEntity(){
            {
                setFirstName(user.getFirstName());
                setLastName(user.getLastName());
                setMobileNo(user.getMobileNo());
                setEmailId(user.getEmailId());
                setLocation(user.getLocation());
            }
        };
    }
}

package com.test.tripproject.services;

import com.test.tripproject.model.entities.UserRegistrationEntity;
import com.test.tripproject.model.repositories.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    private final UserDao userDao;

    public UserService(UserDao userDao){
        this.userDao = userDao;
    }

    public String insertUser(UserRegistrationEntity user){
        int id = userDao.insert(user);
        System.out.println(id + "this is the id>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<");
        return (id != 0) ? String.format("User created") : String.format("User could not be created");
    }
}

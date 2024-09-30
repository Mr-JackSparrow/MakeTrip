package com.test.tripproject.repositories;

import com.test.tripproject.model.dtos.LoginDTO;
import com.test.tripproject.model.entities.UserEntity;

public interface UserDao {

    int insert(UserEntity user);
    LoginDTO findCredentialsByEmailId(String emailId);
}

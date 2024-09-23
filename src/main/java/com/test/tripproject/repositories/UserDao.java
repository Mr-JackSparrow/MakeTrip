package com.test.tripproject.repositories;

import com.test.tripproject.model.dtos.LoginDTO;
import com.test.tripproject.model.entities.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserDao {

    int insert(UserEntity user);
    LoginDTO findCredentialsByEmailId(String emailId);
}

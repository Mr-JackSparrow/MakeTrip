package com.test.tripproject.repositories;

import com.test.tripproject.model.entities.UserEntity;

public interface UserDao {

    int insert(UserEntity user);
    int update(UserEntity user, String existingEmail);
    int delete(String emailId);
    UserEntity findUserByEmailId(String emailId);
}

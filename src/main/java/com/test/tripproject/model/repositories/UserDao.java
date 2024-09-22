package com.test.tripproject.model.repositories;

import com.test.tripproject.model.entities.UserRegistrationEntity;

public interface UserDao {

    int insert(UserRegistrationEntity user);
}

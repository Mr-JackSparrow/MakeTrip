package com.test.tripproject.model.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Getter
@Setter
public class UserEntity {

    private Long userId;
    private String firstName;
    private String lastName;
    private String mobileNo;
    private String emailId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String address;
    private Point location;
    private String password;

}

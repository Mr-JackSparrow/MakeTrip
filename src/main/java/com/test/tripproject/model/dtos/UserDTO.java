package com.test.tripproject.model.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class UserDTO {

    private String firstName;
    private String lastName;
    private String mobileNo;
    private String emailId;
    private String location;
    private String password;

}

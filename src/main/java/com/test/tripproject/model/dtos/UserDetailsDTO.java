package com.test.tripproject.model.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class UserDetailsDTO {

    private Long userId;
    private String firstName;
    private String lastName;
    private String mobileNo;
    private String emailId;
    private String address;
    private Double longitude;
    private Double latitude;
    private String password;

}

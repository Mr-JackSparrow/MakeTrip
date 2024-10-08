package com.test.tripproject.model.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String firstName;
    private String lastName;
    private String mobileNo;
    private String emailId;
    private String address;
    private Double longitude;
    private Double latitude;
}

package com.test.tripproject.model.dtos.responseDTOs.detailsDTOs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Getter
@Setter
public class ResponseUserDetailsDTO {

    private Long userId;
    private String firstName;
    private String lastName;
    private String mobileNo;
    private String emailId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String address;
    private Double longitude;
    private Double latitude;
    private String password;

}

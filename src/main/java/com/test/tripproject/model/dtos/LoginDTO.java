package com.test.tripproject.model.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class LoginDTO {

    private String userName;
    private String password;

}

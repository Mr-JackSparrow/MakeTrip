package com.test.tripproject.model.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRequestDTO {
    private UserDTO userDTO;
    private String email;
}

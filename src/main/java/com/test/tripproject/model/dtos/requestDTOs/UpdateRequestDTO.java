package com.test.tripproject.model.dtos.requestDTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRequestDTO {
    private RequestUpdateUserDTO userDTO;
    private String email;
}

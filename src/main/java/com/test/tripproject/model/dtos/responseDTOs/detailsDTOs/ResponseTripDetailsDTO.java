package com.test.tripproject.model.dtos.responseDTOs.detailsDTOs;

import com.test.tripproject.model.dtos.requestDTOs.RequestCreateUserDTO;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseTripDetailsDTO {

    private Long tripId;
    private Long tripMakerId;
    private String tripDestinationDescription;
    private Date startDate;
    private Date endDate;
    private Long maxParticipants;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Double longitude;
    private Double latitude;

}

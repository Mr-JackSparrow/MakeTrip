package com.test.tripproject.model.dtos.requestDTOs;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class RequestCreateTripDTO {

    private Long tripMakerId;
    private String tripDestinationDescription;
    private Date startDate;
    private Date endDate;
    private Long maxParticipants;
    private Double longitude;
    private Double latitude;
}

package com.test.tripproject.model.dtos.requestDTOs;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class RequestUpdateTripDTO {

    private Long tripId;
    private Date startDate;
    private Date endDate;
    private Long maxParticipants;
}

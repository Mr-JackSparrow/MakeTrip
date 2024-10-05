package com.test.tripproject.model.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.geo.Point;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
public class TripEntity {

    private Long tripId;
    private Long tripMakerId;
    private String tripDestinationDescription;
    private Date startDate;
    private Date endDate;
    private Long maxParticipants;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Point location;

}

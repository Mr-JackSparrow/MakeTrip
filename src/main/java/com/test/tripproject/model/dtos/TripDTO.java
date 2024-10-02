package com.test.tripproject.model.dtos;

import java.time.LocalDateTime;

public class TripDTO {

    private String TripDestination;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long maxParticipants;
    private LocalDateTime createdAt;
    private Double longitude;
    private Double latitude;
    private UserDTO user;

}

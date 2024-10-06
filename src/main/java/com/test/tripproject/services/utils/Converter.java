package com.test.tripproject.services.utils;

import com.test.tripproject.model.dtos.requestDTOs.RequestCreateTripDTO;
import com.test.tripproject.model.dtos.requestDTOs.RequestCreateUserDTO;
import com.test.tripproject.model.dtos.requestDTOs.RequestUpdateTripDTO;
import com.test.tripproject.model.dtos.requestDTOs.RequestUpdateUserDTO;
import com.test.tripproject.model.entities.TripEntity;
import com.test.tripproject.model.entities.UserEntity;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

@Component
public class Converter {

    public static UserEntity convertUpdateUserDtoToUserEntity(RequestUpdateUserDTO user){
        return new UserEntity(){
            {
                Point location = new Point(user.getLongitude(), user.getLatitude());

                setUserId(user.getUserId());
                setFirstName(user.getFirstName());
                setLastName(user.getLastName());
                setMobileNo(user.getMobileNo());
                setEmailId(user.getEmailId());
                setAddress(user.getAddress());
                setLocation(location);

            }
        };
    }

    public static UserEntity convertCreateUserDtoToUserEntity(RequestCreateUserDTO user){
        return new UserEntity(){
            {
                Point location = new Point(user.getLongitude(), user.getLatitude());

                setFirstName(user.getFirstName());
                setLastName(user.getLastName());
                setMobileNo(user.getMobileNo());
                setEmailId(user.getEmailId());
                setAddress(user.getAddress());
                setLocation(location);
                setPassword(user.getPassword());

            }
        };
    }

    public static TripEntity convertCreateTripDtoToTripEntity(RequestCreateTripDTO trip){
        return new TripEntity(){
            {
                Point location = new Point(trip.getLongitude(), trip.getLatitude());

                setTripMakerId(trip.getTripMakerId());
                setTripDestinationDescription(trip.getTripDestinationDescription());
                setStartDate(trip.getStartDate());
                setEndDate(trip.getEndDate());
                setMaxParticipants(trip.getMaxParticipants());
                setLocation(location);

            }
        };
    }

    public static TripEntity convertUpdateTripDtoToTripEntity(RequestUpdateTripDTO trip){
        return new TripEntity(){
            {
                setTripId(trip.getTripId());
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+getTripId());
                setStartDate(trip.getStartDate());
                setEndDate(trip.getEndDate());
                setMaxParticipants(trip.getMaxParticipants());
            }
        };
    }
}

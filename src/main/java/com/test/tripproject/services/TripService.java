package com.test.tripproject.services;

import com.test.tripproject.exceptions.CustomException;
import com.test.tripproject.model.dtos.requestDTOs.RequestCreateTripDTO;
import com.test.tripproject.model.dtos.responseDTOs.detailsDTOs.ResponseTripDetailsDTO;
import com.test.tripproject.model.entities.TripEntity;
import com.test.tripproject.repositories.TripDAO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.test.tripproject.services.utils.Converter.convertCreateTripDtoToTripEntity;

@Service
public class TripService {

    private final TripDAO tripDAO;

    public TripService(TripDAO tripDAO){
        this.tripDAO = tripDAO;
    }

    public int createTrip(RequestCreateTripDTO trip){

        int tripId = tripDAO.create(convertCreateTripDtoToTripEntity(trip));

        if(tripId <= 0L){
            throw new CustomException("TRIP WAS NOT CREATED");
        }else{
            return tripId;
        }
    }

    public ResponseTripDetailsDTO findTripById(Long tripId){

        TripEntity trip = tripDAO.readById(tripId);

        if(trip != null){
            return new ResponseTripDetailsDTO(){
                {
                    setTripId(trip.getTripId());
                    setTripMakerId(trip.getTripMakerId());
                    setTripDestinationDescription(trip.getTripDestinationDescription());
                    setStartDate(trip.getStartDate());
                    setEndDate(trip.getEndDate());
                    setMaxParticipants(trip.getMaxParticipants());
                    setCreatedAt(trip.getCreatedAt());
                    setUpdatedAt(trip.getUpdatedAt());
                    setLongitude(trip.getLocation().getX());
                    setLatitude(trip.getLocation().getY());
                }
            };
        }else{
            throw new CustomException("SOMETHING WENT WRONG, CHECK IF DATA IS CORRECT");
        }
    }

    public List<ResponseTripDetailsDTO> findAllTrips(){

        List<TripEntity> list = tripDAO.readAll();

        if(list != null){

            List<ResponseTripDetailsDTO> newList = new ArrayList<>();

            list.forEach(entity -> {
                ResponseTripDetailsDTO trip = new ResponseTripDetailsDTO();

                trip.setTripId(entity.getTripId());
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + entity.getTripId());
                trip.setTripMakerId(entity.getTripMakerId());
                trip.setTripDestinationDescription(entity.getTripDestinationDescription());
                trip.setStartDate(entity.getStartDate());
                trip.setEndDate(entity.getEndDate());
                trip.setMaxParticipants(entity.getMaxParticipants());
                trip.setCreatedAt(entity.getCreatedAt());
                trip.setUpdatedAt(entity.getUpdatedAt());
                trip.setLongitude(entity.getLocation().getX());
                trip.setLatitude(entity.getLocation().getY());

                newList.add(trip);
            });

            System.out.println(newList.get(0));
            return newList;
        }else{
            throw new CustomException("NO TRIP WAS FOUND");
        }
    }

    public List<ResponseTripDetailsDTO> findAllTripsMadeByUser(){
        return List.of();
    }

    public ResponseTripDetailsDTO findTripMadeByUserById(Long tripId){

        return new ResponseTripDetailsDTO();
    }
}

package com.test.tripproject.services;

import com.test.tripproject.model.dtos.TripDTO;

import java.util.List;

public class TripService {

    public List<TripDTO> findAllTripsMadeByUser(){
        return List.of();
    }

    public TripDTO findTripMadeByUserById(Long tripId){
        return new TripDTO();
    }
}

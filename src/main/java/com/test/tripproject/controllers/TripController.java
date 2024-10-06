package com.test.tripproject.controllers;


import com.test.tripproject.model.dtos.requestDTOs.RequestCreateTripDTO;
import com.test.tripproject.model.dtos.requestDTOs.RequestUpdateTripDTO;
import com.test.tripproject.model.dtos.responseDTOs.detailsDTOs.ResponseTripDetailsDTO;
import com.test.tripproject.services.TripService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class TripController {

    private final TripService tripService;

    public TripController(TripService tripService){
        this.tripService = tripService;
    }

    @RequestMapping(value = "/user/create-trip", method = RequestMethod.POST)
    public ResponseEntity<String> createTrip(@RequestBody RequestCreateTripDTO trip){
        try{
            int tripId = tripService.createTrip(trip);

            return ResponseEntity
                    .ok()
                    .body(
                            String.format(" CREATED TRIP WITH ID : %d", tripId)
                    );
        }catch(Exception e){
            return ResponseEntity
                    .ok()
                    .body(
                            e.getMessage()
                    );
        }
    }

    @GetMapping("/user/trip/{tripId}")
    public ResponseEntity<Object> findTripById(@PathVariable Long tripId){

        try{
            return ResponseEntity
                    .ok()
                    .body(
                            tripService.findTripById(tripId)
                    );
        }catch(Exception e){
            return ResponseEntity
                    .ok()
                    .body(
                            e.getMessage()
                    );
        }
    }

    @GetMapping("/trips")
    public ResponseEntity<List<ResponseTripDetailsDTO>> displayAllTrips(){
        try{
            return ResponseEntity
                    .ok()
                    .body(
                            tripService.findAllTrips()
                    );
        }catch(Exception e){
            return ResponseEntity
                    .ok()
                    .body(
                            null
                    );
        }
    }

    @PutMapping("/user/update-trip")
    public ResponseEntity<String> updateTrip(@RequestBody RequestUpdateTripDTO trip){

        try{
            String msg = tripService.updateTrip(trip);

            return ResponseEntity
                    .ok()
                    .body(msg);
        }catch(Exception e){

            return ResponseEntity
                    .ok()
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/user/trip/delete/{tripId}")
    public ResponseEntity<String> deleteTrip(@PathVariable Long tripId){

        try{
            String msg = tripService.deleteTrip(tripId);

            return ResponseEntity
                    .ok()
                    .body(msg);
        }catch(Exception e){

            return ResponseEntity
                    .ok()
                    .body(e.getMessage());
        }
    }
}

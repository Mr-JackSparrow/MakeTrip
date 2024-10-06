package com.test.tripproject.repositories;

import com.test.tripproject.model.entities.TripEntity;

import java.util.List;

public interface TripDAO {
    int create(TripEntity trip);
    TripEntity readById(Long tripId);
    List<TripEntity> readAll();
    int update(TripEntity trip);
    int delete(Long tripId);
}
